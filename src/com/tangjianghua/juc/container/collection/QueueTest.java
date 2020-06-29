package com.tangjianghua.juc.container.collection;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author tangjianghua
 * @date 2020/6/24
 */
public class QueueTest {

    public static void main(String[] args) {
//        ArrayBlockingQueue<Object> list = new ArrayBlockingQueue<>(100000);
//        LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(100000);
        ConcurrentLinkedQueue<Object> list = new ConcurrentLinkedQueue<>();

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    list.offer(Thread.currentThread().getName() + "--" + j);
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
