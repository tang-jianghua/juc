package com.tangjianghua.juc.class003_alllock;

import java.util.concurrent.Phaser;

/**
 * Phaser 阶段锁
 * 注册5个线程，分四个阶段完成
 * @author tangjianghua
 * date 2020/6/23
 * time 15:55
 */
public class PhaserTest {

    static final WarPhaser marriagePhaser = new WarPhaser();
    public static void main(String[] args) {
        //注册5个线程。
        marriagePhaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person(i+"")).start();
        }
    }

    static class Person implements Runnable{

        private String name;

        public Person(String name) {
            this.name = name;
        }

        /**
         * 设计四个阶段
         */
        @Override
        public void run() {

            System.out.println(name+"号已就位！");
            marriagePhaser.arriveAndAwaitAdvance();

            System.out.println(name+"号技能准备好了！");
            marriagePhaser.arriveAndAwaitAdvance();

            System.out.println(name+"号死了！");
            marriagePhaser.arriveAndAwaitAdvance();

            System.out.println(name+"号已卸载！");
            marriagePhaser.arriveAndAwaitAdvance();
        }
    }


    static class WarPhaser extends Phaser{

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("人到齐了！！！\n");
                    return false;
                case 1:
                    System.out.println("开干！！！\n");
                    return false;
                case 2:
                    System.out.println("人都死了！！！\n");
                    return false;
                case 3:
                    System.out.println("拜拜了您嘞！！！\n");
                    return true;
            }
            return true;
        }

    }
}
