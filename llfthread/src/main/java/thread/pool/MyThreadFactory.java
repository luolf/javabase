package thread.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {
    AtomicInteger cnt=new AtomicInteger();
    public Thread newThread(Runnable r) {
        int c=cnt.getAndIncrement();
        System.out.println("my="+Thread.currentThread().getName());
        return new Thread(r,"name-"+c);
    }
}
