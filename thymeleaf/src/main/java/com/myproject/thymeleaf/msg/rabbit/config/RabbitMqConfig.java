package com.myproject.thymeleaf.msg.rabbit.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanjianjian
 * @since 2021/6/10
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置mandatory为true，才能出发回调函数，无论消息成功还是失败都会强制回调
        rabbitTemplate.setMandatory(true);

        // 生产者消息确认回调函数，发送消息都会调用该回调函数
        /*
        1.ack = false 代表消息没有发送到交换机，或是找不到交换机；
        2.ack = true 代表消息发送到了交换机，若有returnCallBack回调函数则说明没有找到交换机绑定的队列，或是该交换机没有有绑定队列；
        3.ack = true 且没有returnCallBack回调函数，则说明消息发送成功，并且发送到了响应的队列中
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("correlationData 相关数据 = " + correlationData);
                System.out.println("ack 确认情况 = " + ack);
                System.out.println("cause 原因 = " + cause);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("message 消息 = " + message);
                System.out.println("replyCode 回应码 = " + replyCode);
                System.out.println("replyText 回应信息 = " + replyText);
                System.out.println("exchange 交换机 = " + exchange);
                System.out.println("routingKey 路由键 = " + routingKey);
            }
        });

        return rabbitTemplate;
    }
}
