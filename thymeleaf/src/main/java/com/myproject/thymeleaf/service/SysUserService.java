package com.myproject.thymeleaf.service;

import com.myproject.thymeleaf.model.entity.SysUser;

import java.util.Collection;
import java.util.List;

public interface SysUserService {

    SysUser getByName(String userName);

    void insertUser(SysUser sysUser);

    List<SysUser> selectByUserName(Collection<String> userName);
}
