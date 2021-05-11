package com.myproject.patterns.creation.strategy;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class CashContext {

    private AbstractCash abstractCash = null;

    public CashContext(String type) {
        switch (type) {
            case "满100减50":
                abstractCash = new CashReturn("100", "50");
                break;
            case "8折":
                abstractCash = new CashRebate("0.8");
                break;
            default:
                abstractCash = new CashNormal();
        }
    }

    public double getResult(double money) {
        return abstractCash.acceptCash(money);
    }
}
