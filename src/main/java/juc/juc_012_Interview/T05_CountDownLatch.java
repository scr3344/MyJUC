package juc.juc_012_Interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1天剑10个元素到容器中，线程2实现监控元素的个数，当个数到五个时，线程2给出提示并结束
 *
 * 给list添加volatile后，t2能够接到通知，但是，t2线程的死循环很浪费cpu 如果不用死循环，
 * 二千，如果在if和break之间被别的线程打断，得到的结果也不精确
 *
 * 这里使用了wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须保证t2先执行，也就是先让t2监听才行
 *
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 整个通信过程比较繁琐
 *
 * 使用latch(门栓)，代替wait notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countDown方法替代wait和notify
 * countDownLatch不涉及锁定，当count的值为零时当前线程继续运行
 * 当不涉及同步，只是涉及线程通信的时候，用synchronized——wait /notify就显得太重了
 * 这时应该考虑countdownLatch/cyclicbarrier/semaphore
 *
 * @author scr
 * @create 2020-05-07 22:13
 */
public class T05_CountDownLatch {
    //添加volatile，使得t2能够得到通知
    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch t=new T05_CountDownLatch();

        CountDownLatch latch = new CountDownLatch(1);


        new Thread(() -> {

                System.out.println("t2 启动");
                if(t.size() !=5){
                    try {
                      latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                //通知t1继续执行


        },"t2").start();
        new Thread(()->{
            System.out.println("t1 启动");

                for (int i = 0; i < 10; i++) {
                    t.add(new Object());
                    System.out.println("add"+i);
                    if(t.size() ==5){
                        //打开门栓，让t2得以执行
                          latch.countDown();

                    }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }


        },"t1").start();



    }
}
