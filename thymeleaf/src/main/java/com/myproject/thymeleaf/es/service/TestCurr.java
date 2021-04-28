package com.myproject.thymeleaf.es.service;

import java.util.*;

/**
 * @author zhanjianjian
 * @since 2021/3/30
 */
public class TestCurr {

    public static void main(String[] args) throws InterruptedException {

/*        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("i = " + Thread.currentThread().getName());
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
        System.out.println("latch = 主线程执行");*/


/*        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    Thread.sleep(5000 + ThreadLocalRandom.current().nextInt(1000));
                    System.out.println("finish" + index + Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDownLatch.await();// 主线程在阻塞，当计数器==0，就唤醒主线程往下执行。
        System.out.println("主线程:在所有任务运行完成后，进行结果汇总");*/

        /*CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 5; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                }
            }).start();
        }


        countDownLatch.countDown();
        System.out.println("main " + Thread.currentThread().getName());*/

/*        int[][] matrix = new int[][]{new int[]{1, 3, 5, 7}, new int[]{10, 11, 16, 20}, new int[]{23, 30, 34, 60}};
        int target = 13;

        boolean b = searchMatrix(matrix, target);
        System.out.println("b = " + b);*/
/*        int[] arr = {2, 7, 3, 4, 1};
        int[] ints = twoSum(arr, 9);
        if (ints != null) {
            for (int i = 0; i < ints.length; i++) {
                System.out.println("i = " + ints[i]);
            }
        }*/

//        clumsy(8);

        int c = 5;
        int i = c % 4;
        System.out.println("i = " + i);

    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        // 判断数组是否是按顺序排列的
        // 判断目标值是否在数组中
        // 判断第一个数组的最后一个值是否小于下一个数组的第一个值

        boolean boo = false;
        for (int i = 0; i < matrix.length; i++) {

            int count = matrix[i][0];

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    boo = true;
                }
                if (count > matrix[i][matrix[i].length - 1] || matrix[i][matrix[i].length - 1] < matrix[i][j]) {
                    boo = false;
                }

            }
        }

        return boo;
    }

    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     * @param nums
     * @return
     */
/*    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 将拆分的数字当做map的key值去重
        Map<Integer, List<Integer>> listMap = new HashMap<>();


        List<Integer> numList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            numList.add(nums[i]);
        }

        for (int i = 0; i < numList.size(); i++) {
            Integer remove = numList.remove(i);
        }
        return null;
    }*/

    /**
     * 5<<2的意思为5的二进制位往左挪两位，右边补0，5的二进制位是0000 0101 ，
     * 就是把有效值101往左挪两位就是0001 0100 ，
     * 正数左边第一位补0，负数补1，等于乘于2的n次方，十进制位是20
     * <p>
     * <p>
     * &按位与的运算规则是将两边的数转换为二进制位，然后运算最终值，运算规则即(两个为真才为真)1&1=1 , 1&0=0 , 0&1=0 , 0&0=0
     * 3的二进制位是0000 0011 ， 5的二进制位是0000 0101 ， 那么就是011 & 101，由按位与运算规则得知，001 & 101等于0000 0001，最终值为1
     * 7的二进制位是0000 0111，那就是111 & 101等于101，也就是0000 0101，故值为5
     */

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            boolean flag = true;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;
                        break;
                    }
                    t.add(nums[i]);
                }
            }
            if (flag) {
                ans.add(new ArrayList<Integer>(t));
            }
        }
        return ans;
    }

    public static int[] twoSum(int[] arr, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int x = target - arr[i];

            if (map.containsKey(x)) {
                return new int[]{map.get(x), i};
            }
            map.put(arr[i], i);
        }
        return null;
    }


    public static int clumsy(int N) {
        // 查询比参数小的所有正整数（去除0）
        List<Integer> list = new LinkedList<>();
        list.add(N);
        while (N > 0) {

            int a = N - 1;
            N--;
            if (a > 0) {
                list.add(a);
            }
        }


       /* for (Integer integer : list) {
            System.out.println("integer = " + integer);




        }*/

        System.out.println("list.size() = " + list.size());

        int count = 0;

        while (count <= list.size()) {

            Integer integer = list.get(count);
            int i = count % 4;
            switch (i){
                case 0:

            }


            count++;
        }


        if (count < list.size()) {
            Integer integer = list.get(count);


        }

        return 0;
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
/*class BSTIterator {

    public BSTIterator(TreeNode root) {

    }

    public int next() {

    }

    public boolean hasNext() {

    }
}*/
