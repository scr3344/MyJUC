package juc.juc_000;

import java.util.concurrent.*;

/**
 * 创建线程的几种方式：
 * 1.Thread ,
 * 2.Runnable,
 * 3.Executors.newCachedThread
 * @author scr
 * @create 2020-04-01 22:32
 */
public class Test02_HowToCreateThread {
    static  class MyThread extends  Thread{
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }
    static class MyRunnable implements Runnable{
        public void run() {
            System.out.println("Hello MyRunnable!");
        }
    }
    static class MyCall implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("Hello MyCall！");
            return "success";
        }
    }

    public static void main(String[] args) {
        //1.第一种方式
        new MyThread().start();
        //2.第二种方式
        new Thread(new MyRunnable()).start();
        //3.第二张方式的lambda写法
        new Thread(() ->{
            System.out.println("lambda!");
        }).start();
        //4.FutureTask+Callable
        Thread t = new Thread(new FutureTask<String>(new MyCall()));
        t.start();
        //5.线程池
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            System.out.println("Hello ThreadPool");
        });
        service.shutdown();//关闭线程池

    }


}
