package com.myproject.thymeleaf.msg.rabbit.receiver;

import com.myproject.thymeleaf.msg.rabbit.config.DirectRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
@Component
@RabbitListener(queues = DirectRabbitConfig.QUEUE_NAME)//监听的队列名称 TestDirectQueue
public class DirectReceiver1 {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("DirectReceiver 1 消费者收到消息  : " + testMessage.toString());
    }

}
