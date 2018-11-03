package thread.pool;

import thread.test.NormalRunnable;
import thread.test.PubRes;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NewFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);
        PubRes pubRes= PubRes.getInstance();
        System.out.println("availableProcessors="+Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 100; i++) {
            final int index = i;
//            try {
//                Thread.sleep(index * 1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            cachedThreadPool.execute(new NormalRunnable(pubRes));
//            cachedThreadPool.submit(new Callable(){
//                public Integer call() throws Exception {
//                    System.out.println(index+"-starting  "+Thread.currentThread().getName());
//                    Thread.sleep(1);
////                    System.out.println("return   "+Thread.currentThread().getName());
//                    return 99;
//                }
//            });
            ThreadPoolExecutor executor = (ThreadPoolExecutor)cachedThreadPool;
            System.out.println(String.format("getPoolSize=%d,getCorePoolSize=%d,getQueue.size=%d",executor.getPoolSize(),executor.getCorePoolSize(),executor.getQueue().size()));
        }
        cachedThreadPool.shutdown();
    }
}
