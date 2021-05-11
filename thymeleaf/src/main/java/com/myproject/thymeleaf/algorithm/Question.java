package com.myproject.thymeleaf.algorithm;

/**
 * @author zhanjianjian
 * @since 2021/5/10
 */
public class Question {


    /**
     * 查询有序矩阵中是否包含该数字（从矩阵的左下角向上找，或从矩阵的右上角向下找）
     * int[] a = new int[]{1, 2, 8, 9};
     * int[] b = new int[]{2, 4, 9, 12};
     * int[] c = new int[]{4, 7, 10, 13};
     * int[] d = new int[]{6, 8, 11, 15};
     * int[][] arrs = new int[][]{a, b, c, d};
     *
     * @param num 待查询的数字
     * @param arr 目标矩阵
     * @return
     */
    public static boolean includeNum(int num, int[][] arr) {
        int c = arr[0].length - 1;
        int r = 0;

        while (arr.length > r && c >= 0) {
            if (num == arr[r][c]) {
                return true;
            } else if (num > arr[r][c]) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }
}
