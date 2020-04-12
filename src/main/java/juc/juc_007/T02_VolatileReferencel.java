package juc.juc_007;

import java.util.concurrent.TimeUnit;

/**
 *volatile 引用类型（包括数据） 只能保证引用本身的可见性,不能保证内部字段的可见性
 * @author scr
 * @create 2020-04-12 21:00
 */
public class T02_VolatileReferencel {
    boolean running = true;
    volatile  static T02_VolatileReferencel  T= new T02_VolatileReferencel();

    void m(){
        System.out.println("m start");
        while (running){
           /* try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {

        new Thread(T::m,"t1").start();

           try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           T.running = false;
    }
}
