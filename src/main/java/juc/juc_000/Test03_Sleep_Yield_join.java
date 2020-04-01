package juc.juc_000;

/**
 * @author scr
 * @create 2020-04-01 23:40
 */
public class Test03_Sleep_Yield_join {
    public static void main(String[] args) {
        //testSleep();
        testYield();
        //terstJoin();
    }

    /**
     * yield,调用yield后，yield告诉当前线程把运行机会交给线程池中有相同优先级的线程(依然有可能
     * 将刚回去的这个线程拿回来继续执行)
     * yield不能保证，当前线程迅速从运行状态切换到就绪状态。
     * yield只能是将当前线程从运行状态转换到就绪状态，而不能是等待或者阻塞状态。
     *yield的意思就是让出一cpu时间片，给同优先级的线程竞争（自己也参与竞争），谁抢到谁执行。
     */
    private static void testYield() {
        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                System.out.println("Yield" + i);
                if(i%10 ==0){
                    System.out.println("叮" +i);
                    Thread.yield();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("-------B" + i);
                if(i%10 ==0){
                    System.out.println("滴" + i);
                    Thread.yield();
                }
            }
        }).start();
    }

    /**
     * sleep -->睡眠 ，当前线程暂停一段时间让给别的线程去执行。
     * sleep怎么复活（苏醒）？由设置的睡眠时间决定,等睡眠到规定的时间自动苏醒，进入等待执行状态
     */
    private static void testSleep() {
        new Thread(() ->{
            for (int i = 0; i < 100; i++) {
                System.out.println("A = " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    /*
    * join 在自己的当前线程（A）加入调用join的线程（B）来执行，当前的线程（A）等待。等调用的
    * 线程（B）运行完毕，自己（A）再去执行。
    * t1和t2两个线程，在t2中调用t1.join，它就会跑到t1线程去执行，等t1线程执行完毕，t2线程继续
    * 执行（自己join自己没有意义）
    * */
    static void terstJoin(){
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("B " + i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(()->{
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("C"+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t3.start();
        t1.start();
        t2.start();
    }
}
