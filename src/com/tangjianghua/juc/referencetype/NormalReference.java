package com.tangjianghua.juc.referencetype;

/**
 * 强引用
 */
public class NormalReference {
    public static void main(String[] args) {
        Object o = new Object();
        System.gc();
        System.out.println(o==null);
    }
}
