package juc.juc_001;

/**
 * synchronized 关键字
 * 对某个对象加锁
 * @author scr
 * @create 2020-04-02 21:51
 */
public class Test06_Synchronized {
    private int count =10;
    public  void m(){
        synchronized (this){//任何线程要执行下面的代码，必须先拿到this的锁
            count--;
            System.out.println(Thread.currentThread().getName()+"count" + count);
        }
    }
}
