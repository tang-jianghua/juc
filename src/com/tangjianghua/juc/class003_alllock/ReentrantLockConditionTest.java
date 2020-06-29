package com.tangjianghua.juc.class003_alllock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangjianghua
 * date 2020/6/23
 * time 19:08
 */
public class ReentrantLockConditionTest {

    public static void main(String[] args) throws InterruptedException {
        final Lock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();
        final Condition condition2 = reentrantLock.newCondition();
        new Thread(() -> {
            try {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "--await1");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "--signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();
        new Thread(() -> {
            try {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "--await2");
                condition2.await();
                System.out.println(Thread.currentThread().getName() + "--signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(2L);
        try {
            reentrantLock.lock();
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "--signal1");
        } finally {
            reentrantLock.unlock();
        }
        TimeUnit.SECONDS.sleep(2L);
        try {
            reentrantLock.lock();
            condition2.signal();
            System.out.println(Thread.currentThread().getName() + "--signal2");
        } finally {
            reentrantLock.unlock();
        }
    }
}
