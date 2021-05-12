package com.myproject.patterns.creation.strategy;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class StrategyTest {

    public static void main(String[] args) {

        CashContext cashContext = new CashContext("满100减50");
        double result = cashContext.getResult(200.05d);
        System.out.println("result = " + result);

    }
}
