package com.tangjianghua.juc.class003_alllock;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：门闩,倒计时解锁
 * await()阻塞当前线程，知道CountDownLatch减到0
 * @author tangjianghua
 * date 2020/6/23
 * time 11:50
 */
public class CountDownLatchTest {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        final ArrayList<Thread> threads = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            threads.add(new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "--start");
            }, "thread-" + i));
        }
        threads.forEach(Thread::start);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--countDown--"+countDownLatch.getCount());
            countDownLatch.countDown();
        }

        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "--countDown--"+countDownLatch.getCount());
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "--countDown--"+countDownLatch.getCount());
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
