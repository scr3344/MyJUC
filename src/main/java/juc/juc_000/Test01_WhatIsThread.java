package juc.juc_000;

import java.util.concurrent.TimeUnit;

/**
 * 什么叫做线程
 * @author scr
 * @create 2020-04-01 22:01
 */
public class Test01_WhatIsThread {
    private static class T1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }


    public static void main(String[] args) {
        // new T1().run(); // 直接调用run（）方法没有启动一个线程，程序还是在单线程中顺序执行
        // 当调用start（）方法，启用一个新的线程，两条线程并行执行
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Test01_WhatIsThread.main");
        }


    }
}

/**
 * 观察这个程序的数据结果，T1和main会交替输出，这就是程序中有两条不同的执行路径在交叉执行。
 */
