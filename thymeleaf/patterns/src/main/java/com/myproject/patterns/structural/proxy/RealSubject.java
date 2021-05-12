package com.myproject.patterns.structural.proxy;

/**
 * @author zhanjianjian
 * @since 2021/5/12
 */
public class RealSubject extends AbstractSubject {
    @Override
    public void request() {
        System.out.println(" 真正被调用的类。");
    }
}
