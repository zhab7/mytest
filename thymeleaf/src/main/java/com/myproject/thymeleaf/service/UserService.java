package com.myproject.thymeleaf.service;

import com.myproject.thymeleaf.model.entity.SysUser;

public interface UserService {

    SysUser getByName(String userName);
}
