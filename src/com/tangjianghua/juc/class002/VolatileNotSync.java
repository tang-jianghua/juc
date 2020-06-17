package com.tangjianghua.juc.class002;


/**
 * volatile的两个功能
 * 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。（实现可见性）
 * 禁止进行指令重排序。（实现有序性）
 * volatile 只能保证对单次读/写的原子性。i++ 这种操作不能保证原子性。
 * @author tangjianghua
 * date 2020/6/17
 * time 13:58
 */
public class VolatileNotSync {

    volatile int count = 0;

    void m(){
        while (count<10000){
            count++;
            System.out.println(Thread.currentThread().getName()+":"+count);
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        final VolatileNotSync volatilNotSync = new VolatileNotSync();
        for (int i = 0; i < 100; i++) {
            threads[i]=new Thread(volatilNotSync::m);
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
        System.out.println(volatilNotSync.count);
    }
}
