package com.myproject.thymeleaf.controller;

import com.myproject.thymeleaf.model.annotation.Log;
import com.myproject.thymeleaf.model.entity.SysUser;
import com.myproject.thymeleaf.model.req.SysUserReq;
import com.myproject.thymeleaf.model.vo.ResponseBo;
import com.myproject.thymeleaf.service.UserService;
import com.myproject.thymeleaf.shiro.util.MD5Utils;
import com.myproject.thymeleaf.util.BeanConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

@Api(value = "用户Controller")
@Slf4j
@Controller
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/getUser")
    @Log("根据用户名获取用户")
    @ApiOperation("根据用户名获取用户")
    public String getUser(Model model, @RequestParam String userName) {

        SysUser sysUser = userService.getByName(userName);
        model.addAttribute("sysUser", sysUser);
        return "user";
    }

    @PostMapping("/login")
    @Log("登录接口")
    @ApiOperation("登录接口")
    @ResponseBody
    public ResponseBo login(String username, String password, Boolean rememberMe) {
        // 密码MD5加密
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            log.info("当前登录用户：{}", username);
            return ResponseBo.ok();
        } catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @RequiresPermissions(value = "admin") // 权限名称
//    @RequiresRoles(value={"admin", "user"}, logical= Logical.AND) // 角色名称
//    @RequiresUser // 登录用户或者remember me 用户
//    @RequiresAuthentication // 登录用户
    @RequestMapping("/index")
    public String index(Model model) {
        // 登录成后，即可通过Subject获取登录的用户信息
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("sysUser", sysUser);
        return "user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String init(Model model) {
        model.addAttribute("time", new Date());
        return "time";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseBo register(@RequestBody @Valid SysUserReq sysUserReq) {
        System.out.println("username = " + sysUserReq);
        SysUser user = userService.getByName(sysUserReq.getUserName());
        if (user != null) {
            return ResponseBo.error("用户名已注册！");
        }

        String newPassword = MD5Utils.encrypt(sysUserReq.getUserName(), sysUserReq.getPassword());
        SysUser sysUser = BeanConvertUtils.map(sysUserReq, SysUser.class);
        sysUser.setPassword(newPassword);
        sysUser.setStatus(0);
        userService.insertUser(sysUser);
        return ResponseBo.ok();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/403")
    public String forbid() {
        return "403";
    }
}
