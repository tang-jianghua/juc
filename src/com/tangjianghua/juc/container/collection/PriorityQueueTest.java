package com.tangjianghua.juc.container.collection;

import java.util.PriorityQueue;

/**
 * @author tangjianghua
 * @date 2020/6/29
 */
public class PriorityQueueTest {

    public static void main(String[] args) {

        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.offer("c");
        queue.offer("a");
        queue.offer("e");
        queue.offer("b");
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
