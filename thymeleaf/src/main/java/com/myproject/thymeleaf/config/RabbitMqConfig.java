package com.myproject.thymeleaf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhanjianjian on 2020/12/1.
 */
@Configuration
public class RabbitMqConfig {

    @Value(value = "${msg.push.exchange.email}")
    private String emailExchange;

    @Value(value = "${msg.push.routekey.email}")
    private String emailRoutekey;

    @Value(value = "${msg.push.queue.email}")
    private String emailQueue;

    /**
     * 创建email队列
     */
    @Bean
    public Queue emailQueue() {
        return new Queue(emailQueue);
    }

    /**
     * 创建交换机
     */
    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(emailExchange);
    }

    /**
     * 通过routingKey把队列与交换机绑定起来
     */
    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with(emailRoutekey);
    }
}
