package juc.juc_010_AtomicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author scr
 * @create 2020-04-13 22:50
 */
public class T03____SyncVsLongAdder {
    static long count2 = 0L;
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    count3.increment();
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

        System.out.println("LongAdder+"+(end-start)+"------"+count3);
        Object o =new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    synchronized (o){
                        count2++;
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

        System.out.println("syn+"+(end-start)+"------"+count2);

    }
}
