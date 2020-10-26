package com.myproject.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myproject.thymeleaf.mapper.RolePermissionMapper;
import com.myproject.thymeleaf.model.entity.RolePermission;
import com.myproject.thymeleaf.model.entity.SysPermission;
import com.myproject.thymeleaf.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermission> selectByRoles(Collection<String> roleRefIds) {
        return rolePermissionMapper.selectList(Wrappers.<RolePermission>lambdaQuery().in(RolePermission::getRoleId, roleRefIds));
    }
}
