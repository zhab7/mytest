package com.myproject.thymeleaf.algorithm;


public class JoinSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 10, 0, 5, 6, 1, 3, 47, 8, 2, 9, 2, 2};
        int[] partition = partition(arr, 5, 0, 2);

        for (int i = 0; i < partition.length; i++) {
            System.out.println("partition = " + partition[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println("i = " + arr[i]);
        }
    }

    public static void test() {
        int[] arr = new int[]{1, 10, 0, 5, 6, 1, 3, 47, 8, 2, 9};
        int[] help = new int[arr.length];
        int left = 0;
        int mid = arr.length / 2;
        int right = arr.length - 1;
        sortArr(arr, left, mid);
        sortArr(arr, mid + 1, right);
    }

    private static void sortArr(int[] arr, int left, int mid) {
        for (int i = left; i <= mid; i++) {

        }
    }

    public static int[] partition(int[] arr, int L, int R, int p) {
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] < p) {
                swap(arr, ++less, L++);
            } else if (arr[L] > p) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

//    public static void midtest(int[] arr, int num, int left, int right) {
//        int l = left -1;
//        int r = right + 1;
//        for (int i = left; i <= right; i++) {
//            if (arr[i] < num) {
//                // 比较并交换
//                swap(arr, i, l);
//                l++;
//            }
//
//        }


//    }

//    private static void swap(int[] arr, int i, int l) {
//        int temp = arr[i];
//        if (l < 0) {
//            arr[i] = arr[0];
//            arr[0] = temp;
//        } else {
//            arr[i] = arr[l];
//            arr[l] = temp;
//        }
//    }
}
