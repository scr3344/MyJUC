package juc.juc_010_AtomicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author scr
 * @create 2020-04-13 22:50
 */
public class T03____SyncVsLongAdder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong();
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

        System.out.println("time+"+(end-start)+"------"+count3);
    }
}
