package thread.tools;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-19
 * Time: 11:06
 */
public class CyclicBarrierTest {
    public static void main(String[] args){
        final CyclicBarrier cyclicBarrier=new CyclicBarrier(3);
        ExecutorService executorService=Executors.newCachedThreadPool();
         for(int i=0;i<3;i++)
           executorService.submit(new Runnable() {
            public void doWorkTask(int num) throws InterruptedException {
                int times=new Random().nextInt()%1000+1000;
                System.out.println(String.format("%s完成第%d个任务:需耗时%d ",Thread.currentThread().getName(),num,times));
                Thread.sleep(times);
            }
            public void run() {

                try {
                    System.out.println(String.format("%s开始启动",Thread.currentThread().getName()));
                    cyclicBarrier.await();
                    for(int j=1;j<4;j++){
                        doWorkTask(j);
                        cyclicBarrier.await();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }
}
