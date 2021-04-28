package com.myproject.thymeleaf.exception;


import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhanjianjian
 * @since 2021/4/2
 */
@RestControllerAdvice
public class ExceptionHandlerTest {


/*    @ExceptionHandler(Exception.class)
    public String exceptionHandler() {
        return getTraceId();
    }

    private String getTraceId() {
        return MDC.get("X-B3-TraceId");
    }*/


}
