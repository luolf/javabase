package thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-05
 * Time: 14:21
 */
public class ReentrantTest implements Runnable {
    ReentrantLock reentrantLock=new ReentrantLock();
    private  int total=0;
    public static void main(String[] args) {

    }

    public void run()   {
        reentrantLock.lock();
        add();
        reentrantLock.unlock();

    }
    private void add(){

    }
}
