package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-10-31
 * Time: 16:51
 */
public class NormalThread  extends  Thread{
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }
}
