package juc.juc_003;

/**
 * 同步和非同步方法是否可以同时调用？
 * 当然是可以的，方法m1加了锁，但是m2没有啊，随时都可以访问m2
 * @author scr
 * @create 2020-04-03 0:16
 */
public class Test10 {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"m1 start......");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m1 end.....");
    }

    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m2 .......");
    }

    public static void main(String[] args) {
        Test10 t =new Test10();
       /* new Thread(()->t.m1(),"t1").start();
        new Thread(()->t.m2(),"t2").start();*/
        /*new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();*/

        //1.8之前的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m1();
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m2();
            }
        },"t2").start();
    }
}
