package com.myproject.thymeleaf.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by zhanjianjian on 2020/12/4.
 */
@Data
public class SysUserLogiinReq {
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    String userName;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    String password;

    @ApiModelProperty(value = "记住我；true or false")
    boolean rememberMe = false;
}
