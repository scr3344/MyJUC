package juc.juc_002;

/**
 * 分析程序的输出
 * @author scr
 * @create 2020-04-02 22:49
 * 同一个ClassLoader空间，将class load到内存中，它一定是单例，
 * 不同的类加载器就不是了，不同类加载器也不能访问，能访问的就一定是同一个类加载器就是单例
 *
 */
public class Test09  implements  Runnable{
    //要保证输出的数字正确，可以使用synchronized修饰方法或者使用volatile，使用了synchronized就不需要使用volatile了，
    //因为synchronized既保证了原子性又保证了可见性
    private /*volatile*/ int count =100;
/*
* volatile 的特性
* 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。（实现可见性）
* 禁止进行指令重排序。（实现有序性）
* volatile 只能保证对单次读/写的原子性。i++ 这种操作不能保证原子性。
* */
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+"count"+count);
    }

    public static void main(String[] args) {
        Test09 t = new Test09();
        for (int i = 0; i < 100; i++) {
            new Thread(t,"THREAD" + i ).start();
        }
    }
}
