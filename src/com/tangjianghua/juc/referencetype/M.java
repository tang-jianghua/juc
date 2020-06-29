package com.tangjianghua.juc.referencetype;

/**
 * @author tangjianghua
 * @date 2020/6/24
 */
public class M {

    byte[] ints = new byte[1024*1024*10];

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
        super.finalize();
    }
}
