package com.tangjianghua.juc.referencetype;

import java.lang.ref.SoftReference;

/**
 * 软引用
 *  -Xms30m -Xmx30m -XX:+PrintGCDetails
 * @author tangjianghua
 */

public class SoftReferenceTest {

    public static void main(String[] args) {

        SoftReference<byte[]> objectSoftReference = new SoftReference<>(new byte[1024*1024*10]);

        System.out.println(objectSoftReference.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(objectSoftReference.get());

        //再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] bytes = new byte[1024 * 1024 * 15];
        System.out.println(objectSoftReference.get());
    }
}
