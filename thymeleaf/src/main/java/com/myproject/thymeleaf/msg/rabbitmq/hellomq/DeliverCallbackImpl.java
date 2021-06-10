package com.myproject.thymeleaf.msg.rabbitmq.hellomq;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
public class DeliverCallbackImpl implements DeliverCallback {

    @Override
    public void handle(String consumerTag, Delivery message) throws IOException {
        String s = new String(message.getBody());
        System.out.println("s = " + s);

    }
}
