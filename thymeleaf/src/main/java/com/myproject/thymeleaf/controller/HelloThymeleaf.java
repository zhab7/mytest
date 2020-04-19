package com.myproject.thymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/thymeleaf")
public class HelloThymeleaf {

    @GetMapping("/hello")
    public String showModel(Model model) {
        model.addAttribute("msg", "hello, thymeleaf");
        return "hello";
    }

    @GetMapping("/currentTime")
    public String showTime(Model model) {
        model.addAttribute("time", new Date());
        return "time";
    }
}
