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
public class recvTest {

    public static void main(String[] args) {

        try {
            Connection connection = MqConnection.createdConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(SendTest.QUEUE_NAME, false, false, false, null);
            System.out.println(" recv start ");
//            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                String message = new String(delivery.getBody(), "UTF-8");
//                System.out.println(" [x] Received '" + message + "'");
//            };
//            channel.basicConsume(SendTest.QUEUE_NAME, true, deliverCallback, consumerTag -> { });

            DeliverCallbackImpl deliverCallback = new DeliverCallbackImpl();
            channel.basicConsume(SendTest.QUEUE_NAME, deliverCallback, new CancelCallbackImpl());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
