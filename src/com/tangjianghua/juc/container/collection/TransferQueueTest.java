package com.tangjianghua.juc.container.collection;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * @author tangjianghua
 * @date 2020/6/29
 */
public class TransferQueueTest {

    public static void main(String[] args) throws InterruptedException {
//        TransferQueue transferQueue= new LinkedTransferQueue();
        SynchronousQueue transferQueue=  new SynchronousQueue<>(true);


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1L);
                System.out.println(transferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2L);
                System.out.println(transferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        transferQueue.put("a");

    }
}
