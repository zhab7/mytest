package com.myproject.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myproject.thymeleaf.mapper.UserRoleMapper;
import com.myproject.thymeleaf.model.entity.UserRole;
import com.myproject.thymeleaf.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> selectByUserRefId(String userRefId) {
        return userRoleMapper.selectList(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userRefId));
    }
}
