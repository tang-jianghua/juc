package com.tangjianghua.juc.class002;


/**
 * 启动两个线程增加一个变量，当变量增加到100时停止.
 *
 * @author tangjianghua
 * date 2020/6/16
 * time 10:58
 */
public class T {

    private int count;

    private void m() {
        while (true){
            synchronized(this){
                System.out.println(Thread.currentThread().getName() + " : " + count);
                if (count >= 200) {
                    throw new RuntimeException(Thread.currentThread().getName() + "----------park" );
                }
                count++;
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
