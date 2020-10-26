package com.myproject.thymeleaf.service;

import com.myproject.thymeleaf.model.entity.RolePermission;

import java.util.Collection;
import java.util.List;

public interface RolePermissionService {

    /**
     * 通过角色查询对应权限
     *
     * @param roleRefIds
     * @return
     */
    List<RolePermission> selectByRoles(Collection<String> roleRefIds);
}
