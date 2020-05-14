package juc.juc_014_AQS;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author scr
 * @create 2020-05-15 0:34
 */
public class TestReentrantlock {
    private static volatile int i =0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();

    }

}
