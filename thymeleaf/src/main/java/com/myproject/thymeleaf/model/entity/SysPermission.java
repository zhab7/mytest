package com.myproject.thymeleaf.model.entity;

import lombok.Data;

@Data
public class SysPermission extends BaseEntity {

    String permissionName;

    String description;

    String status;
}
