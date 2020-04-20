package juc.juc_011;

import java.util.concurrent.Exchanger;

/**
 * 线程之间数据交换
 * @author scr
 * @create 2020-04-20 13:00
 */
public class T12_TestExchanger {
static Exchanger<String> exchanger =new Exchanger<>();
    public static void main(String[] args) {
        new Thread(()->{
            String s="T1";
            try {
                s=exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"t1").start();

        new Thread(()->{
            String  s= "T2";
            try {
                s=exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"t2").start();

    }
}
