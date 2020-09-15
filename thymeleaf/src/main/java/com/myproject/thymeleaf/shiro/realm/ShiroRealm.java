package com.myproject.thymeleaf.shiro.realm;

import com.myproject.thymeleaf.model.entity.SysUser;
import com.myproject.thymeleaf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    
    /**
    * 获取用户角色和权限
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

    	// 获取用户输入的用户名和密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // 通过用户名到数据库查询用户信息
        SysUser sysUser = userService.getByName(userName);
        
        if (sysUser == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(sysUser.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        return new SimpleAuthenticationInfo(sysUser, password, getName());
    }
}