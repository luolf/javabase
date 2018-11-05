package thread.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:生产者生产指定量的数字到仓库中
 * Date: 2018-11-05
 * Time: 14:57
 */

public class Producer implements Runnable {
    private Integer totals=10;
    private volatile ArrayList<Goods> goodsList  ;
    private ReentrantLock reentrantLock;
    private Condition condition=reentrantLock.newCondition();

    public Producer(ArrayList<Goods> goodsList, ReentrantLock reentrantLock,Condition condition) throws Exception {
        if(goodsList==null ||reentrantLock==null||condition==null ){
            throw new Exception("xxx is null!");
        }
        this.goodsList = goodsList;
        this.reentrantLock = reentrantLock;
        this.condition = condition;
    }

    public void run() {
       String threadName=Thread.currentThread().getName();
       for(int i=0;i<totals;i++){
           reentrantLock.lock();
           product(threadName);
           reentrantLock.unlock();
           try {
               Thread.sleep(new Random(25).nextInt(100));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
    private void product(String threadName){
        if(goodsList.size()<5){
            Goods goods=new Goods(threadName+i,threadName);
            System.out.println(String.format("生产者%s:生产了%s",threadName,goods));
            goodsList.add(goods);
            System.out.println(String.format("生产者%s:%s放入仓库",threadName,goods));
        }else{
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(String.format("生产者:仓库已满！！",threadName));
            }
        }

    }
}
