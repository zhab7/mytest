package com.myproject.thymeleaf.service;

import com.myproject.thymeleaf.model.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    /**
     * 通过用户id查询对应的角色id
     *
     * @param userRefId
     * @return
     */
    List<UserRole> selectByUserRefId(String userRefId);
}
