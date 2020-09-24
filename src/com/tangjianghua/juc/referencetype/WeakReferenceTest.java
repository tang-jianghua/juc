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

        //如果在对象上不仅有弱引用还有强引用，那么弱引用在gc后不会失效。
        byte[] bytes = new byte[1024 * 1024 * 10];
        WeakReference<byte[]> weakReference = new WeakReference<>(bytes);
        //WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1024 * 1024 * 10]);
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
