package juc.juc_000;

/**
 *线程的状态研究
 * * @author scr
 * @create 2020-04-02 20:45
 */
public class Test04_ThreadState {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + i);
            }
        }
    }
    public static void main(String[] args) {
        Thread t = new MyThread();
        //怎么样获取线程的状态？通过getstate（）方法
        System.out.println("t.getState() = " + t.getState());//他是一个new 状态
        t.start();//start之后就是Runable状态（Runable中有两个状态：Ready就绪状态，Running运行状态）
        try {
            t.join();//等待线程t运行结束再运行下面的代码
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //join 之后就会一个terminated状态（结束状态）
        System.out.println("t.getState() = " + t.getState());

    }

}
/**
 * 线程的状态都是由JVM管理，JVM管理时也要通过操作系统。JVM是跑在操作系统上的普通程序
 */