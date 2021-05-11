package com.myproject.thymeleaf.redis.rest;

import com.myproject.thymeleaf.redis.handler.RedisHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhanjianjian
 * @since 2021/5/7
 */
@RestController
@RequestMapping("/rest/redis")
public class RedisRest {

    @Resource
    private RedisHandler redisHandler;

    @GetMapping("/test")
    public String redisTest() {
        redisHandler.test();
        return "success";
    }

    @GetMapping("/vote")
    public String redisVote(@RequestParam String bookId) {
        redisHandler.vote(bookId);
        return "success";
    }

    @GetMapping("/selectVote")
    public String redisSelectVote() {
        redisHandler.selectVote();
        return "success";
    }
}
