package com.tangjianghua.juc.container.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author tangjianghua
 * @date 2020/6/24
 */
public class ListTest {

    public static void main(String[] args) {
        //Thread-safe------------------------------------
//        Vector<Object> list = new Vector<>();
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();


        //Thread-unsafe------------------------------------
//        LinkedList<Object> list = new LinkedList<>();
//        ArrayList<Object> list = new ArrayList<>();


        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    list.add(new Object());
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
