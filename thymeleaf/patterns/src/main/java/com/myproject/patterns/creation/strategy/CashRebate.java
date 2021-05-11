package com.myproject.patterns.creation.strategy;

/**
 * 打折
 *
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class CashRebate extends AbstractCash {

    private double moneyRebate;

    public double getMoneyRebate() {
        return moneyRebate;
    }

    public void setMoneyRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    public CashRebate(String moneyRebate) {
        this.moneyRebate = Double.valueOf(moneyRebate);
    }

    public CashRebate() {
    }

    @Override
    double acceptCash(double money) {
        return money * moneyRebate;
    }
}
