package com.myproject.thymeleaf.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * @author zhanjianjian
 * @since 2021/7/28
 */
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

        List<AbstractMethod> abstractMethods = super.getMethodList(mapperClass);
        abstractMethods.add(new MySqlMethod());
        return abstractMethods;
    }
}
