package com.tangjianghua.juc.class001;

import java.util.concurrent.Executors;

/**
 * 生成线程的几种方式
 *
 * @author tangjianghua
 * 2020/6/16
 */
public class NewThread {


    /**
     * 方法1 继承Thread，重写run方法。
     */
    private static class T1 extends Thread {

        @Override
        public void run() {
            System.out.println("继承Thread，重写run方法！");
        }
    }

    /**
     * 方法2 实现Runable，重写run方法
     */
    private static class R1 implements Runnable {
        @Override
        public void run() {
            System.out.println("实现Runable，重写run方法！");
        }
    }

    public static void main(String[] args) {
        new T1().start();
        new Thread(new R1()).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类重写Run方法");
            }
        }).start();
        new Thread(() -> System.out.println("lambda表达式")).start();
        new Thread(NewThread::run).start();
        Executors.newSingleThreadExecutor().submit(()-> System.out.println("线程池"));
    }

    public static void run() {
        System.out.println("Lambda表达式2");
    }
}
