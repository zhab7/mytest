package com.myproject.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myproject.thymeleaf.mapper.SysRoleMapper;
import com.myproject.thymeleaf.model.entity.SysRole;
import com.myproject.thymeleaf.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;


    @Override
    public List<SysRole> selectByRoleName(Collection<String> roleNameList) {
        return sysRoleMapper.selectList(Wrappers.<SysRole>lambdaQuery().in(SysRole::getRoleName, roleNameList));
    }
}
