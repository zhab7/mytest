package com.myproject.thymeleaf.service.impl;

import com.myproject.thymeleaf.mapper.SysLogMapper;
import com.myproject.thymeleaf.model.entity.SysLog;
import com.myproject.thymeleaf.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public void saveLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }
}
