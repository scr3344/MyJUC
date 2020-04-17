package juc.juc_011;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 还可以指定为公平锁
 * @author scr
 * @create 2020-04-16 0:31
 */
public class T05_ReentrantLock5  extends Thread {
    private static ReentrantLock lock = new ReentrantLock(true); //参数为true表示为公平锁，请比对结果输出

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try{


            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获得锁！");
            }finally{
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 t1 = new T05_ReentrantLock5();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t1);
        thread1.start();
        thread2.start();
    }
}
