package com.myproject.patterns.creation.strategy;

/**
 * 正常付款
 *
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class CashNormal extends AbstractCash {

    @Override
    double acceptCash(double money) {
        return money;
    }
}
