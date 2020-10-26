package com.myproject.thymeleaf.model.entity;

import lombok.Data;

@Data
public class UserRole extends BaseEntity {

    String roleId;

    String userId;

    String status;
}
