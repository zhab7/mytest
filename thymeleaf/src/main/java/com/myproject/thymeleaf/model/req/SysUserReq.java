package com.myproject.thymeleaf.model.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SysUserReq {

    @NotEmpty
    String userName;

    @NotEmpty
    String password;

    /**
     * 性别；0.男；1.女
     */
    Integer sex;

    String phone;

    String address;

    String email;

    @NotEmpty
    String realName;
}
