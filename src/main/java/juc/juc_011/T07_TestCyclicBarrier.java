package juc.juc_011;

import java.util.TreeMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * (c可累可百瑞啊)
 * 循环栅栏，满足要求就放过，然后重新立起栅栏，等满了又推倒，依次循环
 * cyclicBarrier的概念：比如说一个复杂的操作，需要访问数据库，需要访问网络，需要访问文件
 * 有一种方法是顺序执行，挨个执行完，效率非常低，这是一种方式。还有一种可能就是并发执行，
 * 原有是1，2,3顺序执行，现在并发执行不同的线程，分别去干三个不同的工作，必须是这三个线程全部到位了我
 * 才能去进行，这个时候我们就可以用cyclicBarrier
 * @author scr
 * @create 2020-04-19 14:43
 */
public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(20);
         CyclicBarrier barrier = new CyclicBarrier(20,()->{
             System.out.println("满人");
         });
        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，发车！");
            }
        });*/

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
