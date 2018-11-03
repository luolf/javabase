package thread.pool;

import com.sun.javafx.binding.StringFormatter;
import thread.test.NormalCallable;
import thread.test.NormalRunnable;
import thread.test.PubRes;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NewCachedThreadPool {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(new MyThreadFactory());
        PubRes pubRes= PubRes.getInstance();
        System.out.println("availableProcessors="+Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 100; i++) {
            final int index = i;
//            try {
//                Thread.sleep(index * 1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            cachedThreadPool.submit(new Callable(){
                public Integer call() throws Exception {
                    System.out.println(index+"-starting  "+Thread.currentThread().getName());
                    Thread.sleep(1);
//                    System.out.println("return   "+Thread.currentThread().getName());
                    return 99;
                }
            });
            ThreadPoolExecutor executor = (ThreadPoolExecutor)cachedThreadPool;

            System.out.println(String.format("getPoolSize=%d,getCorePoolSize=%d,getQueue.size=%d",executor.getPoolSize(),executor.getCorePoolSize(),executor.getQueue().size()));

        }
        cachedThreadPool.shutdown();
//        System.out.println("main over!");

    }

}

