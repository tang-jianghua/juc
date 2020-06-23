package com.tangjianghua.juc.class003_alllock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：篱栅，栅栏
 * 栅栏上绑定一个任务，当有足够的线程到达栅栏时，触发栅栏的任务。
 * 多个线程通过栅栏，当有足够的线程到达栅栏的某一阶段时，栅栏的这个阶段放行。
 * @author tangjianghua
 * date 2020/6/23
 * time 14:41
 */
public class CyclicBarrierTest {


    public static void main(String[] args) throws InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("满员出发！"));
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"---阻塞");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"---放行");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"---阻塞---"+cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+"---放行");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+"---阻塞---"+cyclicBarrier.getNumberWaiting());

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"---阻塞");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"---放行");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"---阻塞---"+cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+"---放行");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
