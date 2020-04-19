package com.myproject.thymeleaf.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User extends BaseEntity {

    String userName;
    String password;
    String sex;
    String phone;
    String address;
    String email;
    String realName;
}
