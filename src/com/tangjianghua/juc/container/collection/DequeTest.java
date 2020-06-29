package com.tangjianghua.juc.container.collection;

import java.util.Deque;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author tangjianghua
 * @date 2020/6/29
 */
public class DequeTest {
    public static void main(String[] args) {
        BlockingDeque<Object> deque = new LinkedBlockingDeque<>();

        deque.addFirst("a");
        deque.addFirst("c");
        deque.addFirst("d");
        deque.addLast("e");
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollLast());
    }
}
