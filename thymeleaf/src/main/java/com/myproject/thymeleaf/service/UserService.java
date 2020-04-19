package com.myproject.thymeleaf.service;

import com.myproject.thymeleaf.model.entity.User;

public interface UserService {

    User getByName(String userName);
}
