package com.myproject.patterns.structural.proxy;

/**
 * @author zhanjianjian
 * @since 2021/5/12
 */
public class Proxy extends AbstractSubject {

    private AbstractSubject subject;
    @Override
    public void request() {
        if (subject == null) {
            // 被代理的类
            subject = new RealSubject();
        }
        subject.request();
    }
}
