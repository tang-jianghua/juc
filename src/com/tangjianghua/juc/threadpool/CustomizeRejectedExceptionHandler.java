package com.tangjianghua.juc.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author tangjianghua
 * @date 2020/11/26
 */
public class CustomizeRejectedExceptionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(!executor.isShutdown()){
            System.out.println("自定义拒绝策略");
        }
    }
}
