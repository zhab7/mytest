package com.myproject.thymeleaf.controller;


import com.myproject.thymeleaf.model.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/thymeleaf")
public class HelloThymeleaf {

    @Log("执行/thymeleaf/hello方法")
    @GetMapping("/hello")
    public String showModel(Model model) {
        model.addAttribute("msg", "hello, thymeleaf");
        return "hello";
    }

    @Log("执行获取当前时间的方法")
    @GetMapping("/currentTime")
    public String showTime(Model model) {
        model.addAttribute("time", new Date());
        return "time";
    }
}
