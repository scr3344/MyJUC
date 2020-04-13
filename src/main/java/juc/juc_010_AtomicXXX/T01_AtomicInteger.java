package juc.juc_010_AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 * @author scr
 * @create 2020-04-13 22:27
 */
public class T01_AtomicInteger {
    int count1 = 0;
    AtomicInteger count = new AtomicInteger();
    void m(){
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();//count1++;
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t= new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"thread--"+i
                    ));
        }
        threads.forEach((o)->{
            o.start();
        });

        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }

}
