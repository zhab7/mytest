package com.myproject.thymeleaf.model.entity;

import lombok.Data;


@Data
public class SysRole extends BaseEntity {

    String roleName;

    String description;

    String status;

}
