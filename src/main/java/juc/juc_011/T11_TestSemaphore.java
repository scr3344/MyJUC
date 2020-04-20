package juc.juc_011;

import java.util.concurrent.Semaphore;

/**
 * @author scr
 * @create 2020-04-20 12:45
 */
public class T11_TestSemaphore {
    public static void main(String[] args) {
        //Semaphore s= new Semaphore(2);
        //允许多个线程同时执行
        Semaphore s =new Semaphore(2,true);
        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T1 running....");
                Thread.sleep(200);
                System.out.println("T1 running....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T2 running....");
                Thread.sleep(200);
                System.out.println("T2 running....");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
    }

}
