package com.myproject.thymeleaf.test;

public class BubbleSort {
    public static void main(String[] args) {

        int[] arr = new int[]{1, 5, 4, 9, 3, 8, 2, 10, 6, 8};
//        int[] sort = bubbleSort(arr);
        int[] sort = quSort(arr);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i] + " ");
        }
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length > 2) {
            return arr;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
        return arr;
    }

    public static int[] quSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {

            for (int j = arr.length - 1; j > i; j--) {

                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }

        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
