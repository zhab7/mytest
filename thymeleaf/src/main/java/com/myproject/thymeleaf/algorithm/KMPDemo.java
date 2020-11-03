package com.myproject.thymeleaf.algorithm;

public class KMPDemo {

    public static void main(String[] args) {
        String s = "BBC ABCDAB ABCDABCDABDE";
        String p = "ABCDABD";
        char[] schar = s.toCharArray();
        char[] pchar = p.toCharArray();

        matchString(schar, pchar);
    }

    /**
     * 暴力匹配字符串
     *
     * @param s
     * @param p
     */
    private static void matchString(char[] s, char[] p) {
        int sl = s.length;
        int pl = p.length;

        int i = 0;
        int j = 0;
        while (i < sl && j < pl) {
            if (s[i] == p[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == pl) {
            System.out.println("i = " + (i - j));
        }
    }
}
