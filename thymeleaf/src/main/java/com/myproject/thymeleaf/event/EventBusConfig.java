package com.myproject.thymeleaf.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class EventBusConfig {

    @Bean("billFlowEventBus")
    public EventBus eventBus(@Qualifier("billFlowEventListener") BillFlowEventListener billFlowEventListener) {
        AsyncEventBus eventBus = new AsyncEventBus(new ThreadPoolExecutor(3,3, 0, TimeUnit.SECONDS, new ArrayBlockingQueue(3000)));
        eventBus.register(billFlowEventListener);
        log.info("eventBus ==> billFlowEventBus regist success");
        return eventBus;
    }

}