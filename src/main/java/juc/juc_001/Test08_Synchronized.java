package juc.juc_001;

/**
 * synchronized关键字
 * 静态方法中加锁
 * 我们知道，静态方法中没有this对象，你不需要new对象来执行这个方法，但是如果加上synchronized就代表synchronized（Test08_Synchronized.class），这里的
 * synchronized（Test08_Synchronized.class）锁的就是Test08_Synchronized类的对象。
 * @author scr
 * @create 2020-04-02 22:15
 */
public class Test08_Synchronized {
    private  static  int  count =10;

    public static void m(){
        synchronized(Test08_Synchronized.class){ //思考一下，这里写synchronized（this）是否可以？
        count--;
            System.out.println(Thread.currentThread().getName()+"count"+count);
        }
    }
    public synchronized static void mm(){  //这里等同于synchronized(Test08_Synchronized.class)
            count--;
            System.out.println(Thread.currentThread().getName()+"count"+count);
        }
}
