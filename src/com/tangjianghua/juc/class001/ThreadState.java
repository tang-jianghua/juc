package com.tangjianghua.juc.class001;

import java.util.concurrent.locks.LockSupport;

/**
 * 模拟线程状态
 *
 * @author tangjianghua
 * date 2020/6/16
 * time 9:33
 */
public class ThreadState {

    public static void commonState() {
        Thread thread = new Thread(() -> {
            System.out.println("2----------" + Thread.currentThread().getState());
        });
        //刚创建出来状态是NEW
        System.out.println("1----------" + thread.getState());
        //start以后是Runable（Ready->Runing）
        thread.start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("3----------" + thread.getState());
    }

    static Thread t1, t2;

    /**
     * 模拟Waiting
     */
    public static void timedWatingPark() throws InterruptedException {
        t1 = new Thread(() -> {
            System.out.println("start----------" + Thread.currentThread().getState());
            LockSupport.parkNanos(100000000L);
            System.out.println("unpark----------" + Thread.currentThread().getState());
        });
        //刚创建出来状态是NEW
        System.out.println("new----------" + t1.getState());
        t1.start();

        Thread.sleep(50L);
        System.out.println("park----------" + t1.getState());
        Thread.sleep(100L);
        System.out.println("done----------" + t1.getState());
    }
    /**
     * 模拟Waiting
     */
    public static void waitingPark() throws InterruptedException {
        t1 = new Thread(() -> {
            System.out.println("start----------" + Thread.currentThread().getState());
            LockSupport.park();
            System.out.println("unpark----------" + Thread.currentThread().getState());
        });
        //刚创建出来状态是NEW
        System.out.println("new----------" + t1.getState());
        t1.start();

        Thread.sleep(100L);
        System.out.println("park----------" + t1.getState());
        LockSupport.unpark(t1);
        Thread.sleep(100L);
        System.out.println("done----------" + t1.getState());
    }

    /**
     * 模拟Waiting
     */
    public static void timedWaitingWait() throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            System.out.println("start----------" + Thread.currentThread().getState());
            try {
                synchronized (o) {
                    o.wait(100L);
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("unpark----------" + Thread.currentThread().getState());
        });

        //刚创建出来状态是NEW
        System.out.println("new----------" + t1.getState());
        t1.start();
        Thread.sleep(50L);
        System.out.println("park----------" + t1.getState());
        synchronized (o) {
            o.notifyAll();
        }
        Thread.sleep(100L);
        System.out.println("done----------" + t1.getState());
    }

    /**
     * 模拟Waiting
     */
    public static void waitingWait() throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            System.out.println("start----------" + Thread.currentThread().getState());
            try {
                synchronized (o) {
                    o.wait();
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("unpark----------" + Thread.currentThread().getState());
        });

        //刚创建出来状态是NEW
        System.out.println("new----------" + t1.getState());
        t1.start();
        Thread.sleep(100L);
        System.out.println("park----------" + t1.getState());
        synchronized (o) {
            o.notifyAll();
        }
        Thread.sleep(100L);
        System.out.println("done----------" + t1.getState());
    }

    /**
     * 模拟Waiting
     */
    public static void waitingJoin() throws InterruptedException {
        t1 = new Thread(() -> {
            System.out.println("start----------" + Thread.currentThread().getState());
            try {
                LockSupport.unpark(t2);
                t2.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("unpark----------" + Thread.currentThread().getState());
        });

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("park----------" + t1.getState());
        });
        t2.start();

        //刚创建出来状态是NEW
        System.out.println("new----------" + t1.getState());
        t1.start();
        Thread.sleep(100L);
        System.out.println("done----------" + t1.getState());
    }

    /**
     * 模拟Waiting
     */
    public static void timedWaitingJoin() throws InterruptedException {
        t1 = new Thread(() -> {
            System.out.println("start----------" + Thread.currentThread().getState());
            try {
                LockSupport.unpark(t2);
                t2.join(100L);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("unpark----------" + Thread.currentThread().getState());
        });

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("park----------" + t1.getState());
        });
        t2.start();

        //刚创建出来状态是NEW
        System.out.println("new----------" + t1.getState());
        t1.start();
        Thread.sleep(100L);
        System.out.println("done----------" + t1.getState());
    }
    /**
     * 模拟Waiting
     */
    public static void timedWaitingSleep() throws InterruptedException {
        t1 = new Thread(() -> {
            System.out.println("start----------" + Thread.currentThread().getState());
            try {
                LockSupport.unpark(t2);
               Thread.sleep(100L);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("unpark----------" + Thread.currentThread().getState());
        });

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("park----------" + t1.getState());
        });
        t2.start();

        //刚创建出来状态是NEW
        System.out.println("new----------" + t1.getState());
        t1.start();
        Thread.sleep(100L);
        System.out.println("done----------" + t1.getState());
    }


    public static void main(String[] args) throws InterruptedException {
//        commonState();
//        waitingJoin();
//        timedWatingPark();
//        timedWaitingWait();
        timedWaitingJoin();
    }
}
