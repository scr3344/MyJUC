package juc.juc_011;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock 用于代替synchronized
 * 本例中由于m1锁定this。只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * 使用reentrantlock 可以完成同样的功能
 * 需要注意的是，必须要必须要必须---手动释放锁（重要）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁
 * 因此经常在finally中进行锁释放
 * @author scr
 * @create 2020-04-15 23:02
 */
public class T01_ReentrantLock2 {
    Lock lock = new ReentrantLock();

  void m1(){

          try {
              lock.lock(); // synchronized(this)
              for (int i = 0; i < 10; i++) {
              TimeUnit.SECONDS.sleep(1);
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
          }finally {
              lock.unlock(); //开锁
          }

    }
    void m2(){
      try{
          lock.lock();
          System.out.println("m2.......");
      }finally {
          lock.unlock();
      }

    }

    public static void main(String[] args) {
        T01_ReentrantLock2 t= new T01_ReentrantLock2();
        new Thread(t::m1).start();
          try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }
}
