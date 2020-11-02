package com.myproject.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myproject.thymeleaf.mapper.UserMapper;
import com.myproject.thymeleaf.model.entity.SysUser;
import com.myproject.thymeleaf.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public SysUser getByName(String userName) {
        return userMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUserName, userName));
    }

    @Override
    public void insertUser(SysUser sysUser) {
        userMapper.insert(sysUser);
    }
}
