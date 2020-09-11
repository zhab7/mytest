package com.myproject.thymeleaf.model.entity;

import lombok.Data;

@Data
public class User extends BaseEntity {

    String userName;
    String password;

    /**
     * 性别；0.男；1.女
     */
    Integer sex;
    String phone;
    String address;
    String email;
    String realName;

    /**
     * 账户状态；0.是可用；1.冻结
     */
    Integer status;
}
