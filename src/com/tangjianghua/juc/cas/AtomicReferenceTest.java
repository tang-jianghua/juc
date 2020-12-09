package com.tangjianghua.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author tangjianghua
 * @date 2020/12/1
 */
public class AtomicReferenceTest {
    private static AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public static boolean lock() {
        return threadAtomicReference.compareAndSet(null, Thread.currentThread());
    }

    public static boolean unlock() {
        return threadAtomicReference.compareAndSet(Thread.currentThread(), null);
    }


    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    if (AtomicReferenceTest.lock()) {
                        System.out.println(Thread.currentThread().getName() + "获取到锁");
                        TimeUnit.SECONDS.sleep(5);
                        AtomicReferenceTest.unlock();
                        System.out.println(Thread.currentThread().getName() + "释放锁");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    if (AtomicReferenceTest.lock()) {
                        System.out.println(Thread.currentThread().getName() + "获取到锁");
                        TimeUnit.SECONDS.sleep(5);
                        AtomicReferenceTest.unlock();
                        System.out.println(Thread.currentThread().getName() + "释放锁");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread.start();

    }
}
