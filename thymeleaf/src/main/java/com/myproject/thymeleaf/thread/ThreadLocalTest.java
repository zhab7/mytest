package com.myproject.thymeleaf.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanjianjian
 * @since 2021/6/9
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("name_task_%d").build();
   public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 2L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("name = " + Thread.currentThread().getName());

                String s = threadLocal.get();

                System.out.println("ssss = " + s);


                threadLocal.remove();
            }
        });

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String s = threadLocal.get();
                System.out.println("s = " + s);
            }
        });

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                threadLocal.remove();
            }
        });
        System.out.println("args = " + args);
        threadPoolExecutor.shutdown();
    }

}
