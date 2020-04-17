package juc.juc_011;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock 用于代替synchronized
 * 本例中由于m1锁定this。只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * 使用reentrantlock(瑞安创特-唠嗑) 可以完成同样的功能
 * 需要注意的是，必须要必须要必须---手动释放锁（重要）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁
 * 因此经常在finally中进行锁释放
 *
 * 使用reentrantlock可以进行“尝试锁定”tryLock。这样无法锁定，或者在指定时间内无法锁定，线程决定是否继续等待
 *
 * 使用reentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 * @author scr
 * @create 2020-04-15 23:02
 */
public class T01_ReentrantLock4 {



    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
     Thread t1 = new Thread(()->{
         try {
             lock.lock();
             System.out.println("t1 start");
             TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
             System.out.println("t1 End");
         } catch (InterruptedException e) {
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
     });

     t1.start();

     Thread t2 = new Thread(()->{
         try{
             lock.lockInterruptibly(); //可以对interrupt方法做出响应
             System.out.println("t2 start!");
             TimeUnit.SECONDS.sleep(5);
             System.out.println(" t2 end!");
         }catch (InterruptedException e){
             System.out.println("innterrupted!");
         }finally {
         lock.unlock();
         }
     });
     t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt(); //打断线程2的等待
    }
}
