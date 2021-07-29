package com.myproject.thymeleaf.thread;

/**
 * 破坏双亲委派模型测试
 *
 * @author zhanjianjian
 * @since 2021/6/25
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        return MyClassLoader.class;
    }
}

class Test{

    public static void main(String[] args) throws ClassNotFoundException {

       Thread.currentThread().setContextClassLoader(new MyClassLoader());

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Class<?> aClass = contextClassLoader.loadClass("com.myproject.thymeleaf.thread.MyClassLoader");
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);
        Class<?> loadClass = systemClassLoader.loadClass("com.myproject.thymeleaf.thread.MyClassLoader");

        boolean equals = aClass.equals(loadClass);
        System.out.println("equals = " + equals);
        System.out.println("contextClassLoader = " + contextClassLoader);
    }
}
