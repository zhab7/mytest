package com.myproject.thymeleaf;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanjianjian
 * @since 2021/3/10
 */
public class Test {

//    public static void main(String[] args) {
//
//        String s = "asdsdsekm0bFVVcsdT";
//
//        int i = s.hashCode();
//        System.out.println("i = " + i);
//        int j = i % 3;
//        System.out.println("j = " + j);
//
//    }

    /**
     * 将m的所有元素存入本HashMap实例中
     */
/*    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {        //得到 m 中元素的个数
        int s = m.size();        //当 m 中有元素时，则需将map中元素放入本HashMap实例。
        if (s > 0) {            // 判断table是否已经初始化，如果未初始化，则先初始化一些变量。（table初始化是在put时）
            if (table == null) { // pre-size
                // 根据待插入的map 的 size 计算要创建的　HashMap 的容量。
                float ft = ((float)s / loadFactor) + 1.0F;
                int t = ((ft < (float)MAXIMUM_CAPACITY) ?
                        (int)ft : MAXIMUM_CAPACITY);                // 把要创建的　HashMap 的容量存在　threshold　中
                if (t > threshold)
                    threshold = tableSizeFor(t);
            }            // 如果table初始化过，因为别的函数也会调用它，所以有可能HashMap已经被初始化过了。
            // 判断待插入的　map 的 size,若　size 大于　threshold，则先进行　resize()，进行扩容
            else if (s > threshold)
                resize();            //然后就开始遍历 带插入的 map ，将每一个 <Key ,Value> 插入到本HashMap实例。
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                K key = e.getKey();
                V value = e.getValue();                // put(K,V)也是调用　putVal　函数进行元素的插入
                putVal(hash(key), key, value, false, evict);
            }
        }
    }*/
    public static void main(String[] args) {

//        Collections.synchronizedMap(new HashMap<String, String>()).put("sss", "sss");

        String s = "Ea";
        int i = s.hashCode();
        System.out.println("i = " + i);
        int x = i % 16;
        System.out.println("x = " + x);

        String s1 = "FB";
        int j = s1.hashCode();
        System.out.println("j = " + j);
        int y = j % 16;
        System.out.println("y = " + y);
    }
    /**
     * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
     *
     * 输入：stones = [2,7,4,1,8,1]
     * 输出：1
     * 解释：
     * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
     * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
     * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
     * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
     * 输入：stones = [31,26,33,21,40]
     * 输出：5
     * 输入：stones = [1,2]
     * 输出：1
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int weight : stones) {
            sum += weight;
        }
        int n = stones.length, m = sum / 2;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (j < stones[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }
        for (int j = m;; --j) {
            if (dp[n][j]) {
                return sum - 2 * j;
            }
        }
    }

//    class Solution {
        public int lastStoneWeightII1(int[] stones) {
            if (stones == null || stones.length <= 0) {
                return 0;
            }

            int sum = 0;
            for (int stone : stones) {
                sum += stone;
            }

            int maxWeight = sum / 2;    // 根据推导，当石头最小可能重量时，最好子集数组石头的总重量，趋近于 且 最大为 sum / 2
            int length = stones.length;
            boolean[] dp = new boolean[maxWeight + 1];  // dp[i] 表示，是否有子集数组，重量和为i
            dp[0] = true;
            for (int stone : stones) {
                for (int i = maxWeight; i >= stone; i--) {
                    dp[i] = dp[i] || dp[i - stone];
                }
            }

            // 从 最大值 开始，遍历dp数组，寻找最大i的值
            for (int i = maxWeight; ; i--) {
                if (dp[i]) {
                    return sum - 2 * i; // 根据数学推导，计算result
                }
            }
        }
//    }
    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * 示例 2：
     *
     * 输入：nums = [1], target = 1
     * 输出：1
     */


}
