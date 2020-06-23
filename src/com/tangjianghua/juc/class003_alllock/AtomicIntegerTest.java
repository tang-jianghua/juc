package com.tangjianghua.juc.class003_alllock;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tangjianghua
 * date 2020/6/23
 * time 11:32
 */
public class AtomicIntegerTest {

    AtomicInteger atomicInteger = new AtomicInteger(0);
     void m() {
        for (int i = 0; i < 1000000; i++) {
            synchronized(this){
                if (atomicInteger.get() < 100000){
                    System.out.println(Thread.currentThread().getName()+"--"+atomicInteger.incrementAndGet()) ;
                }
            }
        }
    }
    public static void main(String[] args) {

        final AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        final ArrayList<Thread> threads = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(atomicIntegerTest::m,"thread-"+i));
        }
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        System.out.println(atomicIntegerTest.atomicInteger.get());
    }
}
