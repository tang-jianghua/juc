package com.tangjianghua.juc.cas;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * spinlock
 *
 * @author tangjianghua
 * @date 2020/12/1
 */
public class AtomicReferenceLock implements Lock {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();


    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (!Thread.currentThread().isInterrupted()) {
            logger.error("current thread is not interrupted");
        }
        while (!tryLock() && Thread.currentThread().isAlive()) {
            //spin cas
        }
    }

    @Override
    public boolean tryLock() {
        return threadAtomicReference.compareAndSet(null, Thread.currentThread());
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long convert = TimeUnit.MILLISECONDS.convert(time, unit);
        logger.warn("延迟毫秒:" + convert);
        convert += System.currentTimeMillis();
        logger.warn("超时:" + convert);
        while (!tryLock()) {
            if (System.currentTimeMillis() - convert > 0) {
                logger.warn(Thread.currentThread().getName() + "获取锁超时");
                return false;
            }
        }
        return true;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lock() {
        while (!tryLock()) {
            //spin cas
        }
    }

    @Override
    public void unlock() {
        threadAtomicReference.compareAndSet(Thread.currentThread(), null);
    }

    public static void main(String[] args) {
        AtomicReferenceLock atomicReferenceLock = new AtomicReferenceLock();
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
            try {
                if (atomicReferenceLock.tryLock(7L, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + "获取到锁");
                    atomicReferenceLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread.start();
        thread2.start();
    }
}
