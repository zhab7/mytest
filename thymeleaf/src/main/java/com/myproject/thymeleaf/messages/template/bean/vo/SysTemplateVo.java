package com.myproject.thymeleaf.messages.template.bean.vo;

import lombok.Data;

/**
 * Created by zhanjianjian on 2020/12/1.
 */
@Data
public class SysTemplateVo {

    /**
     * 模板类型
     */
    private String templateType;

    /**
     * 模板类型名称
     */
    private String templateTypeText;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 模板内容
     */
    private String template;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名称
     */
    private String operatorName;

    /**
     * 模板启用与否；0.代表启用；1.代表未启用
     */
    private String templateEnable;
}
