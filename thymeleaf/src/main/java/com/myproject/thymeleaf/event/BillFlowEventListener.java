package com.myproject.thymeleaf.event;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhanjianjian
 * @since 2021/3/2
 */
@Slf4j
@Component
public class BillFlowEventListener {

    @Subscribe
    public void testMethod(String message) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("event test message = " + message + " ...");
    }
}
