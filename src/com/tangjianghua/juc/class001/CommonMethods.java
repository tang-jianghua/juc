package com.tangjianghua.juc.class001;

import java.util.concurrent.TimeUnit;

/**
 * Thread的几种常用方法
 *
 * @author tangjianghua
 * 2020/6/16
 */
public class CommonMethods {

    /**
     * 休眠
     */
    public static void sleep() {
        new Thread(() -> {
            System.out.println("休眠2s");
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("休眠结束");
        }).start();
    }

    /**
     * 让出资源
     */
    public static void yield() {

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A------" + i);
                if (i == 50) {
                    System.out.println("让出资源");
                    Thread.yield();
                    System.out.println("重新被调度");
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("B------" + i);
            }
        }).start();
    }


    /**
     * 阻塞直到线程死亡
     */
    public static void join() {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A------" + i);
                if (i == 50) {
                    System.out.println("开始阻塞");
                    try {
                        Thread.currentThread().join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getState());
            try {
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getState());
        }).start();
    }


    public static void main(String[] args) {
//        sleep();
//        yield();
        join();
    }
}
