package thread.tools;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-19
 * Time: 9:50
 */
public class ArrayBlockingQueueTest {
     ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(1,false);
    public  static void main(String[] args){
        ArrayBlockingQueueTest arrayBlockingQueueTest=new ArrayBlockingQueueTest();
        final ArrayBlockingQueueTest.Consumer consumer= arrayBlockingQueueTest.new Consumer() ;
        final ArrayBlockingQueueTest.Producter producter= arrayBlockingQueueTest.new Producter();
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        consumer.get();
//                        try {
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }).start();
        }
        for(int i=0;i<1;i++){
            new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        producter.put(Double.toString(Math.random() * 10));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }
    class Consumer  {
        public String get(){
//            System.out.println(String.format("Thread name:%s,开始消费。。。",Thread.currentThread().getName()));
            String code= null;
            try {
                code = (String) arrayBlockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Thread name:%s,消费结束 %s",Thread.currentThread().getName(),code));
            return code;
        }
        public Consumer(){}
    }
    class Producter  {
        public void put(String code){
//            System.out.println(String.format("Thread name:%s,开始生产 %s",Thread.currentThread().getName(),code));
            try {
                arrayBlockingQueue.put(code);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(String.format("Thread name:%s,生产结束 %s",Thread.currentThread().getName(),code));
        }
        public Producter(){}
    }
}
