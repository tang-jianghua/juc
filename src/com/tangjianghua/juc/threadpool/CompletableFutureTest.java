package com.tangjianghua.juc.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author tangjianghua
 * @date 2020/6/29
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<Object> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
                System.out.println(Thread.currentThread().getName() + "---ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<Object> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
                System.out.println(Thread.currentThread().getName() + "---ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<Object> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
                System.out.println(Thread.currentThread().getName() + "---ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3).join();
        System.out.println("all is ok");
    }
}
