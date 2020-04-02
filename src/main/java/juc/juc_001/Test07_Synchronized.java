package juc.juc_001;

/**
 * synchionized关键字
 * 对某个对象加锁
 * @author scr
 * @create 2020-04-02 22:01
 */
public class Test07_Synchronized {
    private int count =10;
    // 等于在方法的代码上执行synchonized（this）
    public synchronized void m(){
        count--;
        System.out.println(Thread.currentThread().getName()+"count"+count);
    }
}
