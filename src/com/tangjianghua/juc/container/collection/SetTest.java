package com.tangjianghua.juc.container.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author tangjianghua
 * @date 2020/6/24
 */
public class SetTest {

    public static void main(String[] args) {
        //Thread-safe------------------------------------
        Set<String> list = Collections.synchronizedSet(new TreeSet<>());
//        Set<Object> list = Collections.synchronizedSet(new HashSet<>());


        //Thread-unsafe------------------------------------
//        Set<Object> list = new HashSet<>();
//        Set<Object> list = new LinkedHashSet<>();
//        SortedSet<String> list = new TreeSet<>();


        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    list.add(Thread.currentThread().getName()+"--"+j);
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
