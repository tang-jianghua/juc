package com.tangjianghua.juc.threadpool;

import java.util.concurrent.ThreadFactory;

/**
 * @author tangjianghua
 * @date 2020/11/26
 */
public class CustomizeThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"自定义线程名称");
    }
}
