package com.myproject.thymeleaf.msg.rabbitmq.hellomq;

import com.rabbitmq.client.CancelCallback;

import java.io.IOException;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
public class CancelCallbackImpl implements CancelCallback {
    @Override
    public void handle(String consumerTag) throws IOException {
        System.out.println(" test CancelCallback ");
    }
}
