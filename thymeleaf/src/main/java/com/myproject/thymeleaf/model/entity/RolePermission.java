package com.myproject.thymeleaf.model.entity;

import lombok.Data;

@Data
public class RolePermission extends BaseEntity {

    String roleId;

    String permissionId;

    String status;

}
