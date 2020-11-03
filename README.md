一、冒泡排序

临近的两个元素进行比较  ---> 交换 ---> 再比较...

```java
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
```

二、插入排序

后一个个元素同前一个或前多个元素进行比较，若大于则不动；反之与前一个交换，并继续向前进行比较

```java
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
```

三、选择排序

循环找出最小值，然后和对应的索引进行交换

```java
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
```

