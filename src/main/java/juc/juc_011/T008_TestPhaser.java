package juc.juc_011;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * (非ze)--=>阶段
 * phaser 更像是countDownLatch和cyclicBarrier的结合，
 * phaser是按照不同阶段来对线程进行执行，就是他本身维护这一个阶段这样的一个成员变量，当前我是执行到哪个阶段，
 * 是第0个，还是第一个，每个阶段不同的时候这个线程都可以往前走，有的线程走到某个阶段就停了，有的线程会一直走到结束
 * 你的程序中如果说以公道了分好几个阶段执行，而且有的人必须得几个人共同参与的一种情况下可能会用到这个Phaser
 *
 * @author scr
 * @create 2020-04-19 15:24
 */
public class T008_TestPhaser {
    static Random r = new Random();
    static MarriagePhaser phaser= new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            final int nameIndex =i;
            new Thread(()->{
                Person p = new Person("person"+nameIndex);
                p.arrive();
                phaser.arriveAndAwaitAdvance(); //到达等待继续往前走
                p .eat();
                phaser.arriveAndAwaitAdvance();
                p.leave();
                phaser.arriveAndAwaitAdvance();
            }).start();
        }
    }



    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("所有人到齐了");
                    return false;
                case 1:
                    System.out.println("所有人吃完了");
                    return false;
                case 2:
                    System.out.println("所有人离开");
                    System.out.println("婚礼结束");
                    return true;
                default:
                    return true;
            }
        }
    }

    static  void milliSleep(int milli){
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }
        public void arrive(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n" , name);
        }
        public void eat(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完！ \n",name);
        }
        public void leave(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！ \n",name);
        }
    }
}
