package com.tangjianghua.juc.container.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author tangjianghua
 * @date 2020/6/29
 */
public class MapTest {

    public static void main(String[] args) {
        //Thread-safe------------------------------------
//        Map<Object, Object> map =new Hashtable<>();
//        Map<Object, Object> map =new ConcurrentHashMap<>();
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());

        //Thread-unsafe------------------------------------
//        Map<Object, Object> map = new HashMap<>();
//        Map<Object, Object> map = new LinkedHashMap<>();


        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    map.put(Thread.currentThread().getName()+"--"+j,new Object());
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.size());
    }
}
