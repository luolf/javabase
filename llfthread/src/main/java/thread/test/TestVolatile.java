package thread.test;

public class TestVolatile implements  Runnable{
    boolean status = true;

    /**
     * 状态切换为true
     */
    public void stopMe(){
        status = false;
    }

    /**
     * 若状态为true，则running。
     */
    public void run(){
        while(status){
            System.out.println("running....");
        }
    }
    public static void main(String[] arrs){
        TestVolatile testVolatile=new TestVolatile();
        new Thread(testVolatile).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testVolatile.stopMe();
    }
}
