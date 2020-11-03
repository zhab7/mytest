package com.myproject.thymeleaf.model.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SysLog extends BaseEntity {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 响应时间
     */
    private BigDecimal time;

    /**
     * 调用方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 方法执行结果
     */
    private String result;
}
