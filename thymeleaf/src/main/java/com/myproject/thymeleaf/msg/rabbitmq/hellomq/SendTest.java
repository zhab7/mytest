package com.myproject.thymeleaf.msg.rabbitmq.hellomq;

import com.myproject.thymeleaf.msg.rabbitmq.utils.MqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
public class SendTest {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        try {
            // 建立连接
            Connection connection = MqConnection.createdConnection();
            // 创建通道
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "hello rabbit mq";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("send message = " + message);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
