package com.myproject.thymeleaf.algorithm;

import java.util.Arrays;

/**
 * @author zhanjianjian
 * @since 2021/8/12
 */
public class QuickSort {


    public static void quickSort(int[] arr, int start, int end) {
        if (arr.length <= 0) {
            return;
        }
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= temp) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        System.out.println(Arrays.toString(arr));
        quickSort(arr, start, left - 1);
        quickSort(arr, left + 1, end);
    }
}
