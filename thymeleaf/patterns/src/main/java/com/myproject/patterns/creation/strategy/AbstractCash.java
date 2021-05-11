package com.myproject.patterns.creation.strategy;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public abstract class AbstractCash {

    /**
     * 计算待支付的金额
     *
     * @param money
     * @return
     */
    abstract double acceptCash(double money);
}
