package com.myproject.thymeleaf.service;

import com.myproject.thymeleaf.model.entity.SysRole;

import java.util.Collection;
import java.util.List;

public interface SysRoleService {

    List<SysRole> selectByRoleName(Collection<String> roleNameList);


}
