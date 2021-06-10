package com.myproject.thymeleaf.msg.rabbitmq.workspace;

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
public class WorkerTest {

    public static void main(String[] args) {
        try {
            Connection connection = MqConnection.createdConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(NewTaskTest.TASK_QUEUE_NAME, true, false, false, null);
            channel.basicQos(1);

            DeliverCallback deliverCallback = (d, m) -> {
                String message = new String(m.getBody());
                System.out.println("message = " + message);

                try {
                    for (char c : message.toCharArray()) {
                        if (c == '.') {
                            Thread.sleep(2000);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    channel.basicAck(m.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(NewTaskTest.TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {});

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
