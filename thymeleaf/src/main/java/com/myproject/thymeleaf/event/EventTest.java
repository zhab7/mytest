package com.myproject.thymeleaf.event;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhanjianjian
 * @since 2021/3/2
 */
@Component
public class EventTest {

    @Lazy
    @Resource(name = "billFlowEventBus")
    EventBus eventBus;


    public void test() {
        eventBus.post("123123");
    }

}
