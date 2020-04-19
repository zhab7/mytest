package com.myproject.thymeleaf.controller;

import com.myproject.thymeleaf.model.entity.User;
import com.myproject.thymeleaf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUser")
    public String getUser(Model model, @RequestParam String userName) {

        User user = userService.getByName(userName);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/login")
    public String login(){
        return null;
    }
}
