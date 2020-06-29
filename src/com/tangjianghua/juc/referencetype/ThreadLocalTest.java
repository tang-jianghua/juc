package com.tangjianghua.juc.referencetype;

public class ThreadLocalTest {
    static ThreadLocal<String> ThreadLocal = new ThreadLocal();
    static ThreadLocal<String> ThreadLocal2 = new ThreadLocal();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            ThreadLocal.set(Thread.currentThread().getName());
            ThreadLocal2.set(Thread.currentThread().getName()+"---2");
            System.out.println(ThreadLocal.get());
            System.out.println(ThreadLocal2.get());
        });
        Thread thread2 = new Thread(() -> {
            ThreadLocal.set(Thread.currentThread().getName());
            ThreadLocal2.set(Thread.currentThread().getName()+"---2");
            System.out.println(ThreadLocal.get());
            System.out.println(ThreadLocal2.get());
        });
        thread.start();
        thread2.start();
    }
}
