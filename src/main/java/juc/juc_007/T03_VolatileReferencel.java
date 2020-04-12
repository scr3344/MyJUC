package juc.juc_007;

import java.util.concurrent.TimeUnit;

/**
 *volatile 引用类型（包括数据） 只能保证引用本身的可见性,不能保证内部字段的可见性
 * @author scr
 * @create 2020-04-12 21:00
 */
public class T03_VolatileReferencel {
    private static class Data{
         int a,b;
        public Data(int a,int b){
            this.a=a;
            this.b=b;
        }

    }

    volatile static Data data;

    public static void main(String[] args) {

        Thread writer = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                data = new Data(i,i);
            }
        });
        Thread reader = new Thread(()->{
           while (data ==null){}
           int x = data.a;
           int y = data.b;
           if(x!=y){
               System.out.printf("a= %s,b =%s%n", x, y);
           }
        });

        writer.start();
        reader.start();
           try {
              reader.join();
              writer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("end!");
    }
}
