package com.myproject.thymeleaf.test;

public class BubbleSort {
    public static void main(String[] args) {

        int[] arr = new int[]{1, 5, 4, 9, 3, 8, 2, 10, 6, 8};
//        int[] sort = bubbleSort(arr);
//        int[] sort = inSort(arr);
        int[] sort = selectionSort(arr);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(sort[i] + " ");
        }
    }

    public static int[] bubbleSort(int[] arr) {
        // 排序前需先校验是否需要排序

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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

    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            swap(arr, i, index);
        }
        return arr;
    }

    public static int[] quickSort(int[] arr) {




        return arr;
    }
}
