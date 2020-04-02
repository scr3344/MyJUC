package juc.juc_001;

/**
 * synchronized关键字
 * @author scr
 * @create 2020-04-02 21:17
 */
public class Test05_Synchronized {
    private int count =10;
    private Object o = new Object();
    public void  m(){
        //任何线程要执行下面的代码，必须先拿到o的锁
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName()+"count"+count);
        }
    }
}
