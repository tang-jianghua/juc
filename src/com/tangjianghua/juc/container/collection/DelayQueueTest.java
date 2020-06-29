package com.tangjianghua.juc.container.collection;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 执行定时任务
 * @author tangjianghua
 * @date 2020/6/29
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Delayed> delayQueue = new DelayQueue<>();

        long l = System.currentTimeMillis();
        delayQueue.put(new DelayedObject("a",l+2000));
        delayQueue.put(new DelayedObject("b",l+4000));
        while (!delayQueue.isEmpty()){
            System.out.println(((DelayedObject)delayQueue.take()).name);
        }
    }

    static class DelayedObject implements Delayed{

        private String name;

        private long taskTime;

        public DelayedObject(String name, long taskTime) {
            this.name = name;
            this.taskTime = taskTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(taskTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long l = this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS);
            return l<=0?l<0?-1:0:1;
        }
    }
}
