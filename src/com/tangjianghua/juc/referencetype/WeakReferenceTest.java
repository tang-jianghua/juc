package com.tangjianghua.juc.referencetype;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用
 * -Xms15m -Xmx15m -XX:+PrintGCDetails
 * @author tangjianghua
 * @date 2020/6/24
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(weakReference.get());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(weakReference.get());
    }
}
