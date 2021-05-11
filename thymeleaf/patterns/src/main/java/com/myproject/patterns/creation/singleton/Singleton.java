package com.myproject.patterns.creation.singleton;

/**
 * 单例模式
 *
 * @author zhanjianjian
 * @since 2021/5/11
 */
public class Singleton {
    private static Singleton singleton;

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Object.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton singleton = Singleton.getSingleton();
                System.out.println("singleton = " + singleton);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton singleton1 = Singleton.getSingleton();
                System.out.println("singleton1 = " + singleton1);
            }
        }).start();

        Singleton singleton3 = Singleton.getSingleton();
        System.out.println("singleton3 = " + singleton3);
    }
}
