package com.myproject.thymeleaf.algorithm;

/**
 * @author zhanjianjian
 * @since 2021/4/20
 */
public class Test {

    String str = new String("good");
    char[] ch = {'a', 'b', 'c'};

    public static void main(String[] args) {
//        Test test = new Test();
//        change(test.str, test.ch);
//        System.out.println("test.str = " + test.str);
//        System.out.println("test.ch = " + test.ch);

/*        String s;
        System.out.println("s = " + s);*/
    }

    public static void change(String str, char[] ch) {
        str = "It is ok";
        ch[0] = 'g';
    }


}
