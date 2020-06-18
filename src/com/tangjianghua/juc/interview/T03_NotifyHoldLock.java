/**
 * �����������⣺���Ա�����
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
 * �����������������������������
 * @author mashibing
 */
package com.tangjianghua.juc.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class T03_NotifyHoldLock {

	volatile List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}
	
	public static void main(String[] args) {
		T03_NotifyHoldLock c = new T03_NotifyHoldLock();

		final Object lock = new Object();

		new Thread(() -> {
			while(true) {
				synchronized (lock){
					System.out.println("t2 ��ʼ");
					try {
						lock.wait();
					} catch (InterruptedException interruptedException) {
						interruptedException.printStackTrace();
					}
					if(c.size() == 5) {
						break;
					}
				}
			}
			System.out.println("t2 ����");
		}, "t2").start();
		new Thread(() -> {
			for(int i=0; i<10; i++) {
				c.add(new Object());
				System.out.println("add " + i);
				if(i==4){
					synchronized (lock){
						lock.notifyAll();
					}
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();

	}
}
