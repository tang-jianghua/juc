package com.tangjianghua.juc.deamon;

import java.util.concurrent.TimeUnit;

/**
 * @author tangjianghua
 * @date 2020/11/16
 */
public class DaemonTest {

    public static void main(String[] args) {
        Thread run = new Thread(() -> {
            while (true) {
                System.out.println("Daemon---run");
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        run.setDaemon(true);
        new Thread(() -> {
            run.start();
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
