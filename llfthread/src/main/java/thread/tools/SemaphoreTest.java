package thread.tools;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-19
 * Time: 10:48
 */
public class SemaphoreTest {

    public static void main(String[] args){
          final Semaphore semaphore=new Semaphore(4);
        for (int i=0;i<10;i++ ) {
            new Thread(new Runnable() {
                public void run() {
                    try { semaphore.acquire(2);
                        System.out.println(String.format("我是%s",Thread.currentThread().getName()));
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release(2);
                    }


                }
            }).start();
        }
    }
}
