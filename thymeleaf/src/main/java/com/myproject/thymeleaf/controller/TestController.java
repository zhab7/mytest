package com.myproject.thymeleaf.controller;


import com.myproject.thymeleaf.model.annotation.Log;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试Controller")
@RestController
public class TestController {

    @Log("执行方法1")
    @GetMapping("/one")
    public void methodOne(String name) { }

    @Log("执行方法2")
    @GetMapping("/two")
    public void methodTwo() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Log("执行方法3")
    @GetMapping("/three")
    public void methodThree(String name, String age) { }
}