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
    private Integer totals=50;
    private volatile ArrayList<Goods> goodsList  ;
    private ReentrantLock reentrantLock;
    private Condition emptyLock;
    private Condition fullLock ;
    public Consumer(PubRes pubRes) throws Exception {
        this.reentrantLock =pubRes.getReentrantLock();
        this.emptyLock=pubRes.getEmptyLock();
        this.fullLock=pubRes.getFullLock();
        this.goodsList=pubRes.getGoodsList();
        if(goodsList==null ||reentrantLock==null ){
            throw new Exception("xxx is null!");
        }
    }

    public void run() {
        String threadName=Thread.currentThread().getName();
        while(true){
            reentrantLock.lock();
            if(totals==0){
                reentrantLock.unlock();
                break;
            }
            consum(threadName);
            reentrantLock.unlock();
            try{
                Thread.sleep(new Random(25).nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("消费者%s:不再消费！！",threadName));
    }
    private void consum(String threadName){
//        if(totals==0){
//            System.out.println(String.format("~~~~~消费者%s:唤醒其余消费者！！",threadName));
//            emptyLock.signalAll();
//            return ;
//        }
         if(this.goodsList.size()>0){
             Goods goods= this.goodsList.remove(0);
             System.out.println(String.format("消费者%s: 消费oooooooooooo%s",threadName,goods));
             totals--;
//             System.out.println(String.format("消费者%s:仓库可存货！！唤醒生产者",threadName));
             fullLock.signal();

         }else{
             System.out.println(String.format("》》》》》》消费者%s:空仓库阻塞自己等待货物...",threadName));
             try {
                 emptyLock.await();
                 System.out.println(String.format("********************消费者%s:被唤醒...",threadName));
             } catch (InterruptedException e) {
                 System.out.println(String.format("消费者%s:InterruptedException空仓库%s！！",threadName,e.getMessage()));
             }
         }


    }
}
