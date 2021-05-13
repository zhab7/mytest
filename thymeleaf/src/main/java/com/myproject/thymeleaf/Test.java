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

}
