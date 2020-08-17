package com.tangjianghua.juc.threadpool;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auth tangjianghua
 * @date 2020/8/2
 */
public class ScheduledExecuteServiceTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        scheduledThreadPoolExecutor.submit(() -> {
            System.out.println("test1");
        });
        scheduledThreadPoolExecutor.schedule(() -> System.out.println("schedule"),2, TimeUnit.SECONDS);

        scheduledThreadPoolExecutor.shutdown();
    }
}
