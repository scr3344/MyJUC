package juc.juc_011;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author scr
 * @create 2020-04-20 20:33
 */
public class T13_TestLockSupport {
    public static void main(String[] args) {
        Thread t= new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (1==5){
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        LockSupport.unpark(t);
    }
}
