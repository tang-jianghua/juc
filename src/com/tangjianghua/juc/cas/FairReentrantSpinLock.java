package com.tangjianghua.juc.cas;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author tangjianghua
 * @date 2020/12/2
 */
public class FairReentrantSpinLock {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    private Queue<Thread> queue = new LinkedBlockingQueue<>();

    private HashSet<Thread> waiter = new HashSet<>();

    public void lock() {
        if (threadAtomicReference.get() != null && threadAtomicReference.get() == Thread.currentThread()) {
            return;
        }
        if(!waiter.contains(Thread.currentThread())){
            queue.add(Thread.currentThread());
            waiter.add(Thread.currentThread());
        }
        while (!queue.isEmpty()) {
            if (queue.peek() == Thread.currentThread()) {
                while (!threadAtomicReference.compareAndSet(null, queue.peek())) {
                    //spin
                }
                queue.poll();
                return;
            }
        }
    }

    public void unlock() {
        threadAtomicReference.compareAndSet(Thread.currentThread(), null);
    }

    public static void main(String[] args) {
        FairReentrantSpinLock atomicReferenceLock = new FairReentrantSpinLock();
        Thread thread = new Thread(() -> {
            atomicReferenceLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到锁");
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReferenceLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁");
        });
        Thread thread2 = new Thread(() -> {
            atomicReferenceLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到锁");
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReferenceLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁");

        });
        thread.start();
        thread2.start();
    }
}
