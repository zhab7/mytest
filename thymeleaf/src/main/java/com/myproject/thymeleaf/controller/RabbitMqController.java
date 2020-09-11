package com.myproject.thymeleaf.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rest/rabbitMq")
public class RabbitMqController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessageTest")
    public void sendMessageTest(){
//        rabbitTemplate.send();
//        MessageBuilder.withBody("".getBytes())
    }
}
