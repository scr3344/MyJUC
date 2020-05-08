package juc.juc_012_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1天剑10个元素到容器中，线程2实现监控元素的个数，当个数到五个时，线程2给出提示并结束
 * @author scr
 * @create 2020-05-07 22:13
 */
public class T01_WithoutVolatile {
    List list = new ArrayList();
    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T01_WithoutVolatile t=new T01_WithoutVolatile();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("add"+i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() -> {
            while (true){
                if(t.size() ==5){
                    break;
                }
            }
            System.out.println("t2 结束");
        },"t2").start();

    }
}
