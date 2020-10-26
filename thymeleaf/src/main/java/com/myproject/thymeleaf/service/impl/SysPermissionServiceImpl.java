package com.myproject.thymeleaf.service.impl;

import com.myproject.thymeleaf.mapper.SysPermissionMapper;
import com.myproject.thymeleaf.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;



}
