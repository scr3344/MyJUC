package juc.juc_014_AQS;

import java.util.concurrent.locks.Lock;

/**
 * @author scr
 * @create 2020-05-14 23:05
 */
public class main {
    public static int m=0;
    public static Lock lock = new MLock();

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];

        for (int i=0;i<threads.length;i++){
            threads[i] =new Thread(()->{
                try {
                    lock.lock();
                    for(int j=0;j<100;j++){
                        m++;
                    }
                }finally {
                    lock.unlock();
                }

            });
        }
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
        System.out.println(m);
    }

}
