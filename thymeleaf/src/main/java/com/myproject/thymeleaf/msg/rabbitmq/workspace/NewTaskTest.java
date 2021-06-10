package com.myproject.thymeleaf.msg.rabbitmq.workspace;

import com.myproject.thymeleaf.msg.rabbitmq.utils.MqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
public class NewTaskTest {

    public static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) {
        try {
            Connection connection = MqConnection.createdConnection();
            Channel channel = connection.createChannel();
            // 声明队列
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            String message = String.join(" ", args);
            // 发布消息
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("utf-8"));
            System.out.println("task send message = " + message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
