package com.myproject.thymeleaf.messages.template.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by zhanjianjian on 2020/12/1.
 */
@Data
public class TemplateQueryVo {

    /**
     * 模板类型
     */
    @ApiModelProperty(value = "模板类型")
    private List<String> templateTypeList;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private List<String> roleIdList;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private List<String> userIdList;

    /**
     * 操作人id
     */
    @ApiModelProperty(value = "操作人id")
    private List<String> operatorIdList;

    /**
     * 模板启用与否；0.代表启用；1.代表未启用
     */
    @ApiModelProperty(value = "模板启用与否；0.代表启用；1.代表未启用")
    private String templateEnable;


    @ApiModelProperty(value = "初始日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date startOperatorTime;

    @ApiModelProperty(value = "结束日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date endOperatorTime;
}
