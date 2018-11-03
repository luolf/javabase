package thread.test;

import java.util.concurrent.Callable;

public class NormalCallable implements Callable<Integer> {
    public Integer call() throws Exception {
        System.out.println("starting  "+Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("return   "+Thread.currentThread().getName());
        return 99;
    }
}
