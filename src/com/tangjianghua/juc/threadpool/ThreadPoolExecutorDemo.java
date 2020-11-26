package com.tangjianghua.juc.threadpool;

import java.util.concurrent.*;

/**
 * @author tangjianghua
 * @date 2020/11/26
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        //等待队列，必须是阻塞式的
        BlockingQueue<Runnable> queue;
        //有界队列
        queue = new LinkedBlockingDeque(2);
        //queue = new ArrayBlockingQueue(2);
        //queue = new SynchronousQueue(true);
        //无界队列  任务会处在队列中，如果宕机会丢失
        //queue = new LinkedTransferQueue<>();


        //拒绝策略
        RejectedExecutionHandler rejectedExecutionHandler;
        //拒绝时抛出异常
        rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        //如果线程池没有关闭，直接执行run方法
        rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        //直接忽略
        rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
        //尝试执行最老的任务，执行不了就忽略
        rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();

        rejectedExecutionHandler = new CustomizeRejectedExceptionHandler();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, queue, new CustomizeThreadFactory(), rejectedExecutionHandler);

        for (int i = 0; i < 1000; i++) {
            final String s = i + "";
            threadPoolExecutor.submit(() -> {
                System.out.println(s);
            });
        }
        threadPoolExecutor.shutdown();
    }
}
