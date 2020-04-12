package juc.juc_007;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 * A B 线程都用到一个变量，Java默认是在A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会吧running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，
 * 并不会每次都去读取堆内存。这样，当主线程修改running的值之后，t1线程感知不到，所有不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存汇总读取running的值
 *
 *
 * volatile并不能保证多个线程共同修改running变量时带来的不一致问题，也就是说volatile不能代替synchronized
 * @author scr
 * @create 2020-04-12 16:54
 */
public class T01_HelloVolatile {
   volatile boolean running = true; // 对比有无volatile的情况下，整个程序运行结果的区别
void m(){
    System.out.println("m start");
    while (running){
    }
    System.out.println("m end!");
}

    public static void main(String[] args) {
        T01_HelloVolatile t= new T01_HelloVolatile();
        new Thread(t::m,"t1").start();
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        }, "t1").start();*/
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running= false;
    }
}
