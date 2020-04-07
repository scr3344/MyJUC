package juc.juc_005;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有了某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的。
 * @author scr
 * @create 2020-04-04 0:29
 */
public class Test {
    synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }

    public static void main(String[] args) {
        new Test().m1();
    }
}
/**
 * 疑问：
 * 1.同一把锁的2个synchronized方法，分别被两个线程调用，同一时间会被锁住吗？--会被锁住，必须等待前一个线程调用完毕
 * 2.不同锁的两个方法，可以方法A调用方法B吗，会有什么效果？--
 * */