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
    private Integer totals=100;
    private volatile ArrayList<Goods> goodsList  ;
    private ReentrantLock reentrantLock;
    private Condition emptyLock;
    private Condition fullLock ;
    public static void main(String[] args) throws Exception {
        PubRes pubRes=PubRes.getInstance();
        Producer producer=new Producer(pubRes);
        Consumer consumer=new Consumer(pubRes);
        for(int i=0;i<10;i++){
            new Thread(consumer).start();
        }
        for(int i=0;i<10;i++){
            new Thread(producer).start();
        }

    }
    public Producer(PubRes pubRes) throws Exception {
        this.reentrantLock =pubRes.getReentrantLock();
        this.emptyLock=pubRes.getEmptyLock();
        this.fullLock=pubRes.getFullLock();
        this.goodsList=pubRes.getGoodsList();
        if(goodsList==null ||reentrantLock==null){
            throw new Exception("xxx is null!");
        }
    }

    public void run() {
       String threadName=Thread.currentThread().getName();
       while(totals>0){
           reentrantLock.lock();
           product(threadName);
           reentrantLock.unlock();
           try {
               Thread.sleep(new Random(25).nextInt(100));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
        System.out.println(String.format("生产者%s:结束生产！！",threadName));
    }
    private void product(String threadName){
        if(totals==0){
            System.out.println(String.format("~~~~~生产者%s:唤醒其余生产者！！",threadName));
            fullLock.signalAll();
            return ;
        }
        if(goodsList.size()<50){
            Goods goods=new Goods(threadName+":"+totals,threadName);
            System.out.println(String.format("生产者%s:生产了%s",threadName,goods));
            goodsList.add(goods);
            totals--;
//            System.out.println(String.format("生产者%s:%s放入仓库，唤醒消费者",threadName,goods));
            emptyLock.signalAll();
        }else{
            try {
                System.out.println(String.format("》》》》》》生产者%s:仓库已满！！阻塞自己",threadName));
                fullLock.await();
                System.out.println(String.format("********************生产者%s:被唤醒！！",threadName));
//                Thread.sleep(1000);
//                System.out.println(String.format("********************生产者%s:循环结束！！",threadName));
            } catch (InterruptedException e) {
                System.out.println(String.format("生产者%s:InterruptedException仓库已满%s！！",threadName,e.getMessage()));
            }finally {
//                System.out.println(String.format("生产者%s:仓库已满！！",threadName));
            }
        }



    }
}
