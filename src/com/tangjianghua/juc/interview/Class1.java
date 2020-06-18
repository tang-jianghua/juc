package com.tangjianghua.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 淘宝面试题：
 * -实现一个容器，提供给两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * -面试题：写一个固定容量同步容器，拥有put和get放啊，以及getCount方法，能够支持2个生产者线程以及10个
 * 消费者线程的阻塞调用
 *
 * @author tangjianghua
 * date 2020/6/18
 * time 14:35xia
 */
public class Class1 {

    private volatile List<Object> objectList = new ArrayList<>();

    public synchronized void add(Object object) {
        objectList.add(object);
    }

    public int size() {
        return objectList.size();
    }

    static Thread thread, thread1;

    public static void main(String[] args) throws InterruptedException {
//        park2();
        wait1();
    }

    public static void park() throws InterruptedException {

        final Class1 class1 = new Class1();
        thread = new Thread(() -> {
            while (class1.size() < 10) {
                LockSupport.park();
                class1.add(new Object());
                System.out.println(Thread.currentThread().getName() + ":" + class1.size());
                LockSupport.unpark(thread1);
            }
        }, "1");
        thread1 = new Thread(() -> {
            while (class1.size() < 5) {
                LockSupport.unpark(thread);
                LockSupport.park();
            }
            System.out.println(Thread.currentThread().getName() + ":" + class1.size());
        }, "2");
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
    }

    static volatile boolean b = true;

    public static void park2() throws InterruptedException {

        final Class1 class1 = new Class1();
        thread = new Thread(() -> {
            while (class1.size() < 10) {
                LockSupport.park();
                if (!b) return;
                class1.add(new Object());
                LockSupport.unpark(thread1);
            }
        }, "1");
        thread1 = new Thread(() -> {
            while (class1.size() < 5) {
                LockSupport.unpark(thread);
                LockSupport.park();
            }
            b = false;
            LockSupport.unpark(thread);
        }, "2");
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(Thread.currentThread().getName() + ":" + class1.size());
    }

    public static void wait1() throws InterruptedException {

        final Class1 class1 = new Class1();
        final Object lock = new Object();
        thread = new Thread(() -> {
            while (class1.size() < 10) {
                synchronized (lock) {
                    try {
                        lock.wait();
                        if (!b) return;
                        class1.add(new Object());
                        System.out.println(Thread.currentThread().getName() + ":add " + class1.size());
                        Thread.sleep(1000);
                        lock.notifyAll();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }, "1");
        thread1 = new Thread(() -> {
            while (class1.size() < 5) {
                synchronized (lock) {
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
            synchronized (lock) {
                b = false;
                lock.notifyAll();
            }
        }, "2");
        thread.start();
        TimeUnit.SECONDS.sleep(1L);
        thread1.start();
        thread.join();
        thread1.join();
    }
}
