package com.myproject.thymeleaf.shiro.realm;

import com.myproject.thymeleaf.model.entity.RolePermission;
import com.myproject.thymeleaf.model.entity.SysUser;
import com.myproject.thymeleaf.model.entity.UserRole;
import com.myproject.thymeleaf.service.SysUserService;
import com.myproject.thymeleaf.service.impl.RolePermissionServiceImpl;
import com.myproject.thymeleaf.service.impl.UserRoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private RolePermissionServiceImpl rolePermissionService;

    /**
     * 获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        // 根据用户名查询用户的基本信息
        log.info("用户：{}，已登录，获取角色权限中=======", user.getUserName());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 根据用户名查询该用户对应的角色信息
        List<UserRole> userRoles = userRoleService.selectByUserRefId(user.getUserName());
        if (CollectionUtils.isNotEmpty(userRoles)) {
            Set<String> roleRefIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());

            List<RolePermission> rolePermissions = rolePermissionService.selectByRoles(roleRefIds);

            // 根据角色信息查询该角色对应的权限
            Set<String> permissionRefIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());

            // 设置角色
            simpleAuthorizationInfo.setRoles(roleRefIds);
            // 设置权限
            simpleAuthorizationInfo.setStringPermissions(permissionRefIds);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取用户输入的用户名和密码
        String userName = (String) token.getPrincipal();
        if (StringUtils.isBlank(userName)) {
            return null;
        }
        String password = new String((char[]) token.getCredentials());
        // 通过用户名到数据库查询用户信息
        SysUser sysUser = sysUserService.getByName(userName);

        if (sysUser == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(sysUser.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        return new SimpleAuthenticationInfo(sysUser, password, getName());
    }

//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof JWTToken;
//    }
}