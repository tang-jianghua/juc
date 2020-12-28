package com.tangjianghua.juc.referencetype;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    static ThreadLocal<String> threadLocal = new ThreadLocal();
    static ThreadLocal<String> threadLocal2 = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
  /*      Thread thread = new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName());
            threadLocal2.set(Thread.currentThread().getName()+"---2");
            System.out.println(threadLocal.get());
            System.out.println(threadLocal2.get());
        });
        Thread thread2 = new Thread(() -> {
            threadLocal.set(Thread.currentThread().getName());
            threadLocal2.set(Thread.currentThread().getName()+"---2");
            System.out.println(threadLocal.get());
            System.out.println(threadLocal2.get());
        });
        thread.start();
        thread2.start();*/
        threadLocal.set("test");
        TimeUnit.SECONDS.sleep(2L);
        System.out.println(threadLocal.get());
        System.gc();
        System.out.println(threadLocal.get());

    }

}
