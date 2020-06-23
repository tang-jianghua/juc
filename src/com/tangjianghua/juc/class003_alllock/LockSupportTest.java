package com.tangjianghua.juc.class003_alllock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author tangjianghua
 * date 2020/6/23
 * time 16:51
 */
public class LockSupportTest {

    public static void main(String[] args) {
        final Thread thread = new Thread(() -> {
            System.out.println("阻塞");
            LockSupport.park();
            System.out.println("已解锁");
        });
        thread.start();

        try {
            Thread.sleep(1000);
            System.out.println("解锁");
            LockSupport.unpark(thread);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
