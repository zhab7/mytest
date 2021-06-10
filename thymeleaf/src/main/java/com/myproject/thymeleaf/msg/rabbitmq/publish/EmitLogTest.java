package com.myproject.thymeleaf.msg.rabbitmq.publish;

import com.myproject.thymeleaf.msg.rabbitmq.utils.MqConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
public class EmitLogTest {

    public static final String EXCHANGE_NAME = "exchange_test";


    public static void main(String[] args) {
        try {
            Connection connection = MqConnection.createdConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = "exchange test";

            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println("exchange send message = " + message);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
