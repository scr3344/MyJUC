package juc.juc_004;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 面试题，模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 *
 * 容易产生脏读问题（dirtyRead）
 * 解决办法就是将读取方法也加上锁
 * @author scr
 * @create 2020-04-03 0:56
 */
public class Account {
    String name;
    double balance;
    public synchronized void  set(String name, double balance){
        this.name =name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance =balance;
    }
    public /*synchronized*/ double getBalance(){
        return this.balance;
    }

    public static void main(String[] args) {
        Account a =new Account();
        new Thread(()->a.set("悟空",1000)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1："+a.getBalance());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2："+a.getBalance());
    }

}
