package com.tangjianghua.juc.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author tangjianghua
 * @date 2020/6/29
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        FutureTask futureTask = new FutureTask<>(new Task(1,2));
        try {
            new Thread(futureTask).start();

            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class Task implements Callable<Integer> {

        private int a;

        private int b;

        public Task(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(5L);
            return a + b;
        }
    }
}
