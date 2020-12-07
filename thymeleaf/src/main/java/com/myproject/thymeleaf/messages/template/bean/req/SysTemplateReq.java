package com.myproject.thymeleaf.messages.template.bean.req;

import com.myproject.thymeleaf.model.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by zhanjianjian on 2020/12/1.
 */
@Data
public class SysTemplateReq extends BaseEntity {

    /**
     * 模板类型
     */
    @NotEmpty(message = "模板类型不能为空")
    private String templateType;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 模板内容
     */
    private String template;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 模板启用与否；0.代表启用；1.代表未启用
     */
    private String templateEnable;
}
