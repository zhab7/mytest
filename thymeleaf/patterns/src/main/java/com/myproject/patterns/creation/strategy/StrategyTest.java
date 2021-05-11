package com.myproject.patterns.creation.strategy;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class StrategyTest {

    public static void main(String[] args) {

        CashContext cashContext = new CashContext("8折");
        double result = cashContext.getResult(20d);
        System.out.println("result = " + result);

    }
}
