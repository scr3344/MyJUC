package juc.juc_010_AtomicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author scr
 * @create 2020-04-13 22:50
 */
public class T02_AtomicVsSyncVsLongAdder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong();
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    count1.incrementAndGet();
                }
            });
        }
        long start =System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end =System.currentTimeMillis();

        System.out.println("time+"+(end-start)+"------"+count1);

        // -----------------
        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000000; j++) {
                        synchronized (lock){
                            count2++;
                        }
                    }
                }
            });
        }
        start =System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end =System.currentTimeMillis();
        System.out.println("sync+"+(end-start)+"------"+count2);

        //-----------------
        for (int i = 0; i < threads.length; i++) {
            threads[i] =new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000000; j++) {
                        count3.increment();
                    }
                }
            });

        }
        start =System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end =System.currentTimeMillis();
        System.out.println("LongAdder+"+(end-start)+"------"+count3);


    }
}
