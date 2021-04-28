package com.myproject.thymeleaf.algorithm;

/**
 * Created by zhanjianjian on 2021/1/14.
 */
public class questTest {


    public static String longestCommonPrefix(String[] strs) {
        // 异常处理
        if (strs.length < 1) {
            return "";
        }

        // .indexOf()
        // 基准字符串
        String baseStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(baseStr) != 0) {
                if (baseStr.length() == 0) {
                    return "";
                }
                baseStr = baseStr.substring(0, baseStr.length() - 1);
            }
        }
        return baseStr;
    }

    public static void main(String[] args) {

        /*String[] strings = new String[]{"flower", "ow", "flight"};

        String s = longestCommonPrefix(strings);
        System.out.println("s = " + s);*/
        String s1 = "sss";
        String s2 = "ss";
        int i = s1.indexOf(s2);
        System.out.println("i = " + i);
    }
}
