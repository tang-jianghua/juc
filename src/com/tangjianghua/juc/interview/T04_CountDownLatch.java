/**
 * �����������⣺���Ա�����
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * <p>
 * �����������������������������
 *
 * @author mashibing
 */
package com.tangjianghua.juc.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class T04_CountDownLatch {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T04_CountDownLatch c = new T04_CountDownLatch();

        final CountDownLatch countDownLatch = new CountDownLatch(1);


        new Thread(() -> {
            System.out.println("t2 ");
            try {
                countDownLatch.await();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if (c.size() == 5) {
                System.out.println("t2 ");
            }
        }, "t2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);
                if (i == 4) {
                    countDownLatch.countDown();
                    return;
                }
            }
        }, "t1").start();
    }
}
