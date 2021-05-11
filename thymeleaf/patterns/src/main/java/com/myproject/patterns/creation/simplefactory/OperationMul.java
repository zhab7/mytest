package com.myproject.patterns.creation.simplefactory;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class OperationMul extends Operation {
    @Override
    public Double getOneNumber() {
        return super.getOneNumber();
    }

    @Override
    public Double getTwoNumber() {
        return super.getTwoNumber();
    }

    @Override
    public Double getResult() {
        return getOneNumber() * getTwoNumber();
    }

}
