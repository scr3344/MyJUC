package juc.juc_011;

import java.util.concurrent.TimeUnit;

/**
 * reentrantlock 用于代替synchronized
 * 本例中由于m1锁定this。只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 * @author scr
 * @create 2020-04-15 23:02
 */
public class T01_ReentrantLock1 {
    synchronized  void m1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if(i==2){
                m2();
            }
        }
    }
   synchronized void m2(){
       System.out.println("m2.......");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 t= new T01_ReentrantLock1();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
