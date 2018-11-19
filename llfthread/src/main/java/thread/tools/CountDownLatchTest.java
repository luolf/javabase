package thread.tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-19
 * Time: 14:29
 */
public class CountDownLatchTest {
    public static void main(String[] args){
        final CountDownLatchTest countDownLatchTest=new CountDownLatchTest();
        final Consumer consumer=countDownLatchTest.new Consumer();
        final Producter producter=countDownLatchTest.new Producter();
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                public void run() {
                    consumer.get();
                }
            }).start();
        }
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                public void run() {
                    producter.put(Thread.currentThread().getName());

                }
            }).start();
        }
    }
    CountDownLatch countDownLatch=new CountDownLatch(10);
    //    public static CountDownLatch getCountDownLatch(int count){
//        return new CountDownLatch(count);
//    }
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10,false);
    public int getSize(){
        return arrayBlockingQueue.size();
    }
    class Consumer {
        Semaphore semaphore=new Semaphore(1);
        public String get(){
            System.out.println(String.format("%s等待货物装满",Thread.currentThread().getName()));

            String s=null;
            try {
                countDownLatch.await();
                System.out.println(String.format("%s发现货物装满", Thread.currentThread().getName()));

                while (arrayBlockingQueue.size() > 0){
                    semaphore.acquire();
                    if(arrayBlockingQueue.size() > 0) {
                        Thread.sleep(500);
                        s = (String) arrayBlockingQueue.take();
                        System.out.println(String.format("%s取到%s", Thread.currentThread().getName(), s));
                    }
                    semaphore.release();

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        }
    }
    class Producter{
        //        CountDownLatch countDownLatch;
//        public Producter(CountDownLatch countDownLatch){
//            this.countDownLatch=countDownLatch;
//        }
        Semaphore semaphore=new Semaphore(10);
        public void put(String s){
            try {
                semaphore.acquire(10);
                Thread.sleep(500);
                arrayBlockingQueue.put(s);
                System.out.println(String.format("%s放入",s));
                countDownLatch.countDown();
                semaphore.release(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
