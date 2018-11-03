package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NewSingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+":"+index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
//            ThreadPoolExecutor executor = (ThreadPoolExecutor)singleThreadExecutor;
//            System.out.println(String.format("getPoolSize=%d,getCorePoolSize=%d,getQueue.size=%d",executor.getPoolSize(),executor.getCorePoolSize(),executor.getQueue().size()));
        }
        singleThreadExecutor.shutdown();
    }
}
