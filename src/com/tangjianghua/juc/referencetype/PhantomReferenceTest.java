package com.tangjianghua.juc.referencetype;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangjianghua
 * @date 2020/6/24
 */
public class PhantomReferenceTest {

    static List<Object> list = new ArrayList<>();
    static ReferenceQueue<M> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) {

        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), referenceQueue);
        new Thread(() -> {
            for (; ; ) {
                list.add(new Object());
            }
        }).start();

        while (true) {
            Reference<? extends M> poll = referenceQueue.poll();
            if (poll != null) {
                System.out.println(poll);
                M m = poll.get();
                System.out.println(m);
            }
        }
    }
}
