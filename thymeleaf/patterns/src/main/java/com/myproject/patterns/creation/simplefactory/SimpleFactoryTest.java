package com.myproject.patterns.creation.simplefactory;

import java.util.Scanner;

/**
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class SimpleFactoryTest {

    /**
     * 简单工厂模式：
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        Double oneNumber = scanner.nextDouble();
        System.out.println("请输入运算符（+、-、*、/）：");
        String operator = scanner.next();
        System.out.println("请输入第二个数字：");
        Double twoNumber = scanner.nextDouble();

        Operation operation = OperationFactory.createOperation(operator);
        operation.setOneNumber(oneNumber);
        operation.setTwoNumber(twoNumber);
        Double result = operation.getResult();
        System.out.println("运算结果 = " + result);
    }

}
