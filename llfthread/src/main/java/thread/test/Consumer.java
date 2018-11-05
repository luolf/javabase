package thread.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-05
 * Time: 16:33
 */
public class Consumer implements  Runnable {
    private Integer totals=10;
    private volatile ArrayList<Goods> goodsList  ;
    private ReentrantLock reentrantLock;
    private Condition condition ;

    public Consumer(ArrayList<Goods> goodsList, ReentrantLock reentrantLock,Condition condition) throws Exception {
        if(goodsList==null ||reentrantLock==null||condition==null ){
            throw new Exception("xxx is null!");
        }
        this.goodsList = goodsList;
        this.reentrantLock = reentrantLock;
        this.condition = condition;
    }

    public void run() {
        String threadName=Thread.currentThread().getName();
        while(totals>0){
            reentrantLock.lock();
            consum(threadName);
            reentrantLock.unlock();
            try{
                Thread.sleep(new Random(25).nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void consum(String threadName){
         if(this.goodsList.size()>0){
             Goods goods= this.goodsList.remove(0);
             System.out.println(String.format("消费者%s:%s移出仓库",threadName,goods));
         }else{
             System.out.println(String.format("消费者%s:空仓库等待...",threadName));

         }

    }
}
