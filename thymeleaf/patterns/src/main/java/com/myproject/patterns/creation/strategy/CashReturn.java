package com.myproject.patterns.creation.strategy;

/**
 * 满 ** 返 **
 *
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class CashReturn extends AbstractCash {

    /**
     * 满足金额
     */
    private double moneyCondition;

    /**
     * 减免的金额
     */
    private double moneyReturn;

    public CashReturn(String moneyCondition, String moneyReturn) {
        this.moneyCondition = Double.valueOf(moneyCondition);
        this.moneyReturn = Double.valueOf(moneyReturn);
    }

    public CashReturn() {
    }

    public double getMoneyCondition() {
        return moneyCondition;
    }

    public void setMoneyCondition(double moneyCondition) {
        this.moneyCondition = moneyCondition;
    }

    public double getMoneyReturn() {
        return moneyReturn;
    }

    public void setMoneyReturn(double moneyReturn) {
        this.moneyReturn = moneyReturn;
    }

    @Override
    double acceptCash(double money) {
        if (money >= moneyCondition) {
            money = money - (Math.round(money / moneyCondition) * moneyReturn);
        }
        return money;
    }
}
