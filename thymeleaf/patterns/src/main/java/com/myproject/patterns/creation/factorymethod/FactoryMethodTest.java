package com.myproject.patterns.creation.factorymethod;

/**
 * @author zhanjianjian
 * @since 2021/5/12
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
//        LeiFengFactory factory = new UndergraduateFactory();
        LeiFengFactory factory = new VolunteerFactory();

        LeiFeng leiFeng = factory.createLeiFeng();
        leiFeng.sweep();
        leiFeng.wash();
    }
}
