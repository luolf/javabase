package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-10-31
 * Time: 17:00
 */
public class NormalRunnable implements Runnable{
    private String name=Thread.currentThread().getName();
    public void run() {
            this.addMe();
    }
    public   void  addMe(){
        synchronized(NormalRunnable.class) {
            value++;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            System.out.println(String.format("name is %s,value=%d", name, value));
        }
    }
}
