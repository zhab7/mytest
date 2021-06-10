package com.myproject.thymeleaf.msg.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
public class MqConnection {

    public static Connection createdConnection() throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("212.129.135.144");
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("test");
        connectionFactory.setPort(5672);
        return connectionFactory.newConnection();
    }
}
