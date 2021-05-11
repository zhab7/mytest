package com.myproject.patterns.creation.simplefactory;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class Operationdel extends Operation {
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
        if (getTwoNumber() == null || getTwoNumber().compareTo(0d) == 0 || getOneNumber() == null) {
            throw new RuntimeException("您输入的数字不合法。");
        }
        return getOneNumber() / getTwoNumber();
    }

}
