package juc.juc_011;

import java.util.concurrent.CountDownLatch;

/**倒数的门栓，latch.await()等待，当countDown结束直到减为0，打开门栓，继续往下执行
 * 这样就可以控制线程什么时候往下执行了
 * join只能等线程结束，才能往下执行，countDown比他灵活
 * @author scr
 * @create 2020-04-16 23:46
 */
public class T06_TestCountDownLatch {
    public static void main(String[] args) {
        usingJoin();
        usingCountDoenLatch();
    }
    private static void usingCountDoenLatch(){
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] =new Thread(()->{
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result+=j;
                }
                latch.countDown();
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch!");

    }

    private static void usingJoin(){
        Thread[] threads =new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i]= new Thread(()->{
               int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result +=j;
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end join");
    }
}
