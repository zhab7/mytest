package com.myproject.thymeleaf.redis.handler;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanjianjian
 * @since 2021/5/7
 */
@Component
public class RedisHandler {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void test() {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        if (connectionFactory == null) {
            throw new RuntimeException("连接报错");
        }
        connectionFactory.setDatabase(2);
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.opsForValue().set("key1", "sss", 10L, TimeUnit.SECONDS);
        redisTemplate.opsForZSet().add("zsetkey", "sss", 15);


    }


}
