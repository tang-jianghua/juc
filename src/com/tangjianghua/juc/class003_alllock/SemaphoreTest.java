package com.tangjianghua.juc.class003_alllock;

import java.util.concurrent.Semaphore;

/**
 * 控制线程并发量
 * @author tangjianghua
 * date 2020/6/23
 * time 16:39
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        /*//创建一个Semaphore，指定并发量为2，公平竞争
        final Semaphore semaphore = new Semaphore(2, true);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"----获取到了锁");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }*/

        Semaphore semaphore1 = new Semaphore(0);
        System.out.println(semaphore1.availablePermits());
        semaphore1.release();
        System.out.println(semaphore1.availablePermits());
        semaphore1.release();
        System.out.println(semaphore1.availablePermits());
        semaphore1.release();
        System.out.println(semaphore1.availablePermits());
    }
}
