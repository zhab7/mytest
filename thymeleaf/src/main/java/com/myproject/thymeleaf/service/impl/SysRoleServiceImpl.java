package com.myproject.thymeleaf.service.impl;

import com.myproject.thymeleaf.mapper.SysRoleMapper;
import com.myproject.thymeleaf.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;


}
