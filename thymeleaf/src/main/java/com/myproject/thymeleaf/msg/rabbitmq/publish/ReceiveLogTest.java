package com.myproject.thymeleaf.msg.rabbitmq.publish;

import com.myproject.thymeleaf.msg.rabbitmq.utils.MqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
public class ReceiveLogTest {

    public static void main(String[] args) {
        try {
            Connection connection = MqConnection.createdConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EmitLogTest.EXCHANGE_NAME, "fanout");

            String queueName = channel.queueDeclare().getQueue();
            System.out.println("queue = " + queueName);

            channel.queueBind(queueName, EmitLogTest.EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (d, m) -> {
                String message = new String(m.getBody());
                System.out.println("message = " + message);
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
