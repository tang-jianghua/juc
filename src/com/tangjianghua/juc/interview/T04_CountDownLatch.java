/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * <p>
 * 分析下面这个程序，能完成这个功能吗？
 *
 * @author mashibing
 */
package com.tangjianghua.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class T04_CountDownLatch {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T04_CountDownLatch c = new T04_CountDownLatch();

        final CountDownLatch countDownLatch = new CountDownLatch(1);


        new Thread(() -> {
            System.out.println("t2 开始");
			try {
				countDownLatch.await();
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
			if (c.size() == 5) {
                System.out.println("t2 结束");
            }
        }, "t2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (i == 4) {
                    countDownLatch.countDown();
                    return;
                }
            }
        }, "t1").start();

    }
}
