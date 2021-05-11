package com.myproject.patterns.creation.simplefactory;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class Operation {

    private Double oneNumber;
    private Double twoNumber;


    public Double getResult() {
        return 0d;
    }

    public Double getOneNumber() {
        return oneNumber;
    }

    public void setOneNumber(Double oneNumber) {
        this.oneNumber = oneNumber;
    }

    public Double getTwoNumber() {
        return twoNumber;
    }

    public void setTwoNumber(Double twoNumber) {
        this.twoNumber = twoNumber;
    }
}
