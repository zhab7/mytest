package com.myproject.thymeleaf.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanjianjian
 * @since 2021/7/28
 */
@Configuration
@MapperScan(value = "com.myproject.thymeleaf.**.mapper*")
public class MyBatisPlusConfig {

    /**
     * 自定义 SqlInjector
     * 里面包含自定义的全局方法
     */
    @Bean
    public MySqlInjector gsCustomSqlInjector() {
        return new MySqlInjector();
    }
}
