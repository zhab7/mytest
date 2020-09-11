package com.myproject.thymeleaf.controller;

import com.myproject.thymeleaf.model.annotation.Log;
import com.myproject.thymeleaf.model.entity.User;
import com.myproject.thymeleaf.model.vo.ResponseBo;
import com.myproject.thymeleaf.service.UserService;
import com.myproject.thymeleaf.shiro.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(value = "用户Controller")
@Controller
//@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUser")
    @Log("根据用户名获取用户")
    @ApiOperation("根据用户名获取用户")
    public String getUser(Model model, @RequestParam String userName) {

        User user = userService.getByName(userName);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/login")
    @Log("登录接口")
    @ApiOperation("登录接口")
    @ResponseBody
    public ResponseBo login(String username, String password){
// 密码MD5加密
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
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

    @RequestMapping("/index")
    public String index(Model model) {
        // 登录成后，即可通过Subject获取登录的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
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
}
