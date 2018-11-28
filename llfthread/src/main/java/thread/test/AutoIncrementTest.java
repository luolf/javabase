package thread.test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:测试使用不同方法同步多线程数字自增性能
 * 多线程无同步、单线程、CAS多线程、synchronized多线程、ReentrantLock多线程、
 * User: luolifeng
 * Date: 2018-11-23
 * Time: 14:04
 */
public class AutoIncrementTest {
    public final static int totalCnt=100000000;
    public static void main(String[] args){
        AutoIncrementTest autoIncrementTest=new AutoIncrementTest();
        final IncrementTaskUseSyn syn= autoIncrementTest.new IncrementTaskUseSyn();
        final IncrementTaskUseLock lock= autoIncrementTest.new IncrementTaskUseLock();
        final IncrementTaskUseCAS cas= autoIncrementTest.new IncrementTaskUseCAS();
        final CountDownLatch countDownLatch =new CountDownLatch(2);
        final CountDownLatch lockCountDown =new CountDownLatch(2);
        final CountDownLatch casCountDown =new CountDownLatch(2);
        long singleStart=System.currentTimeMillis();
        int cnt=0;
        for(int i=0;i<totalCnt*2;i++){
            cnt++;
        }
        AutoIncrementTest.print("singleThread",cnt,System.currentTimeMillis()-singleStart);

        long singleStart2=System.currentTimeMillis();

        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                int cnt2=0;
                public void run() {
                    for(int j=0;j<totalCnt;j++){
                        cnt2++;
                    }
                }
            }).start();
        }
        AutoIncrementTest.print("mulThreadNOSyn",totalCnt*2,System.currentTimeMillis()-singleStart2);

        long synStart=System.currentTimeMillis();
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                public void run() {
                    for(int i=0;i<totalCnt;i++){
                        syn.job();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AutoIncrementTest.print("synchronized",syn.getCounter(),System.currentTimeMillis()-synStart);


        long lockStart=System.currentTimeMillis();
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                public void run() {
                    for(int i=0;i<totalCnt;i++){
                        lock.job();
                    }
                    lockCountDown.countDown();
                }
            }).start();
        }
        try {
            lockCountDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AutoIncrementTest.print("lock",lock.counter,System.currentTimeMillis()-lockStart);


        long atomicStart=System.currentTimeMillis();
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                public void run() {
                    for(int i=0;i<totalCnt;i++){
                        cas.job();
                    }
                    casCountDown.countDown();
                }
            }).start();
        }
        try {
            casCountDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AutoIncrementTest.print("cas",cas.getCounter(),System.currentTimeMillis()-atomicStart);

    }
    public static void  print(String name,int rst,long value){
        System.out.println(String.format("%s:%d:%d",name,rst,value));
    }
    class IncrementTaskUseSyn{
        public  int counter=0;
        public synchronized void job(){
            counter++;
        }
        public int getCounter(){
            return counter;
        }
    }
    class IncrementTaskUseLock{
        ReentrantLock lock=new ReentrantLock();
        public  int counter=0;
        public void job(){
            lock.lock();
            counter++;
            lock.unlock();

        }
        public int getCounter(){
            return counter;
        }
    }

    class IncrementTaskUseCAS{
          AtomicInteger cas=new AtomicInteger();
        public void job(){
            cas.incrementAndGet();
        }
        public int getCounter(){
            return cas.get();
        }
    }
}
