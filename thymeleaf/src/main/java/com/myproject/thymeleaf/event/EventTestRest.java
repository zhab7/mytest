package com.myproject.thymeleaf.event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhanjianjian
 * @since 2021/3/2
 */
@RestController
@RequestMapping("/rest/event")
public class EventTestRest {

    @Resource
    private EventTest eventTest;

    @GetMapping("/test")
    public String testEvent() {

        eventTest.test();
        System.out.println(" test finish ...");
        return " main method finished.";
    }
}

