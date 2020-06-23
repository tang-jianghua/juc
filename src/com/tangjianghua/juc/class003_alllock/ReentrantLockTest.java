package com.tangjianghua.juc.class003_alllock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangjianghua
 * date 2020/6/23
 * time 17:00
 */
public class ReentrantLockTest {

    final static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"--阻塞获取锁");
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName()+"--获取到锁");
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } finally {
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName()+"--释放");
            }
        }).start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"尝试获取锁："+reentrantLock.tryLock());


        final Condition condition = reentrantLock.newCondition();

    }
}
