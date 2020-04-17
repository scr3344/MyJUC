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
 * @author scr
 * @create 2020-04-15 23:02
 */
public class T01_ReentrantLock3 {
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

    /**
     * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据trylock的返回值来判断是否锁定
     * 也可以指trylock的时间，由于trylock（time）抛出异常，所以注意unlock的处理必须放到finally中
     */
    void m2(){
        boolean locked = false;
      try{
          locked = lock.tryLock(5,TimeUnit.SECONDS);
          System.out.println("m2......."+ locked);
      }catch (InterruptedException e) {
          e.printStackTrace();
      }finally {
          if(locked){
              lock.unlock();
          }
      }

    }

    public static void main(String[] args) {
        T01_ReentrantLock3 t= new T01_ReentrantLock3();
        new Thread(t::m1).start();
          try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }
}
