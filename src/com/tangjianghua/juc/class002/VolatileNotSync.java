package com.tangjianghua.juc.class002;


/**
 * volatile的两个功能
 * 底层实现：
 *     内存屏障
 * 内存屏障分为两种：Load Barrier 和 Store Barrier即读屏障和写屏障。
 * 内存屏障有两个作用：
 *
 * 1.阻止屏障两侧的指令重排序；
 * 2.强制把写缓冲区/高速缓存中的脏数据等写回主内存，让缓存中相应的数据失效。
 *
 * 对于Load Barrier来说，在指令前插入Load Barrier，可以让高速缓存中的数据失效，强制从新从主内存加载数据；
 * 对于Store Barrier来说，在指令后插入Store Barrier，能让写入缓存中的最新数据更新写入主内存，让其他线程可见。
 * java的内存屏障通常所谓的四种即LoadLoad,StoreStore,LoadStore,StoreLoad实际上也是上述两种的组合，完成一系列的屏障和数据同步功能。
 *
 * LoadLoad屏障：对于这样的语句Load1; LoadLoad; Load2，在Load2及后续读取操作要读取的数据被访问前，保证Load1要读取的数据被读取完毕。
 * StoreStore屏障：对于这样的语句Store1; StoreStore; Store2，在Store2及后续写入操作执行前，保证Store1的写入操作对其它处理器可见。
 * LoadStore屏障：对于这样的语句Load1; LoadStore; Store2，在Store2及后续写入操作被刷出前，保证Load1要读取的数据被读取完毕。
 * StoreLoad屏障：对于这样的语句Store1; StoreLoad; Load2，在Load2及后续所有读取操作执行前，保证Store1的写入对所有处理器可见。它的开销是四种屏障中最大的。在大多数处理器的实现中，这个屏障是个万能屏障，兼具其它三种内存屏障的功能
 *
 * volatile的内存屏障策略非常严格保守，非常悲观且毫无安全感的心态：
 *
 * 在每个volatile写操作前插入StoreStore屏障，在写操作后插入StoreLoad屏障；
 * 在每个volatile读操作前插入LoadLoad屏障，在读操作后插入LoadStore屏障；
 *
 * 由于内存屏障的作用，避免了volatile变量和其它指令重排序、线程之间实现了通信，使得volatile表现出了锁的特性。
 *
 * 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。（实现可见性）
 * 禁止进行指令重排序。（实现有序性）
 * @author tangjianghua
 * date 2020/6/17
 * time 13:58
 */
public class VolatileNotSync {

    volatile int count = 0;

    /*synchronized*/ void m(){
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        final VolatileNotSync volatilNotSync = new VolatileNotSync();
        for (int i = 0; i < 100; i++) {
            threads[i]=new Thread(volatilNotSync::m);
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 100; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        System.out.println(volatilNotSync.count);
    }
}
