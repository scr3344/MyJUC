package juc.juc_005;

import java.util.concurrent.TimeUnit;

/**
 * 模拟一个父类子类的概念，父类synchronized，子类调用super.m的时候必须得可重入，否则就会出现问题（调用父类是同一把锁）
 * 所谓的重入锁就是你拿着这把锁之后不停地加锁加锁，加好几道，
 * 但是锁定的还是同一个对象，去一道就减1，就是这个概念
 *
 * 一个同步方法可以调用另一个同步方法
 *
 * @author scr
 * @create 2020-04-05 11:34
 */
public class Test02 {
    synchronized void m(){
        System.out.println("m start");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new TT().m();

    }

}
class TT extends Test02{
    @Override
    synchronized void m() {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}
