package com.tangjianghua.juc.class003_alllock;

import java.util.ArrayList;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author tangjianghua
 * date 2020/6/23
 * time 11:42
 */
public class LongAdderTest {

    LongAdder count = new LongAdder();

    void m() {
        for (int i = 0; i < 1000000; i++) {
            synchronized(this){
                if (count.intValue() < 100000){
                    count.increment();
                    System.out.println(Thread.currentThread().getName()+"--"+count.intValue()) ;
                }
            }
        }
    }
    public static void main(String[] args) {

        final LongAdderTest longAdderTest = new LongAdderTest();
        final ArrayList<Thread> threads = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(longAdderTest::m,"thread-"+i));
        }
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        System.out.println(longAdderTest.count.intValue());
    }
}
