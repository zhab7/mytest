package com.myproject.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myproject.thymeleaf.mapper.UserMapper;
import com.myproject.thymeleaf.model.entity.SysUser;
import com.myproject.thymeleaf.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

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

    @Override
    public List<SysUser> selectByUserName(Collection<String> userName) {
        return userMapper.selectList(Wrappers.<SysUser>lambdaQuery().in(SysUser::getUserName, userName));
    }
}
