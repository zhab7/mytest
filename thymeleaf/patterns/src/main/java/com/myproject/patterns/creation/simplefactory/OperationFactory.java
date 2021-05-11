package com.myproject.patterns.creation.simplefactory;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class OperationFactory {

    public static Operation createOperation(String operate) {
        Operation operation = null;
        switch (operate) {
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMul();
                break;
            case "/":
                operation = new Operationdel();
                break;
                default:
                    throw new RuntimeException("运算符不正确或未添加，请检查。");
        }
        return operation;
    }
}
