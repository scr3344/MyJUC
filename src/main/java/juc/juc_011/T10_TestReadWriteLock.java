package juc.juc_011;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  读写锁，读写锁的概念其实是共享锁和排他锁，读锁就是共享锁，写锁就是排他锁，
 * @author scr
 * @create 2020-04-19 17:13
 */
public class T10_TestReadWriteLock {
    private static int value;
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        Runnable readR = ()->read(readLock);
        Runnable writeR = ()->write(writeLock,new Random().nextInt());

        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }


    public static void read(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over！");
            //模拟读取操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static void write(Lock lock,int v){
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("weite over！");
            //模拟写操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
