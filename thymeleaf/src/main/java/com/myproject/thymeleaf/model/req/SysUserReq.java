package com.myproject.thymeleaf.model.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class SysUserReq {

    @NotEmpty(message = "用户名不能为空")
    String userName;

    @NotEmpty(message = "密码不能为空")
    String password;

    /**
     * 性别；0.男；1.女
     */
    Integer sex;

    String phone;

    String address;

    String email;

    @NotEmpty(message = "真实姓名不能为空")
    String realName;

    boolean rememberMe = false;
}
