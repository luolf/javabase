package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-10-31
 * Time: 17:00
 */
public class NormalRunnable implements Runnable{
    public void run() {
            System.out.println(Thread.currentThread().getName());
    }
}
