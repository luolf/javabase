package thread.test;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-10-31
 * Time: 17:02
 */
public class APP {
    public static void main(String[] args){

        //new APP(11,ThreadType.RUNNABLE).creatAndRun();
        new APP(11,ThreadType.CALLABLE).creatAndRun();

    }
    private void init(){
        pubRes =PubRes.getInstance();
        pubRes.setTickets(5);
    }
    private PubRes pubRes;
    private int threadCount=10;
    private ThreadType threadType=ThreadType.THREAD;
    public APP(){}
    public APP(int threadCount,ThreadType threadType){
        this.threadCount=threadCount;
        this.threadType=threadType;
        init();
    }
    public void creatAndRun(){

        if(threadType==ThreadType.THREAD){
            creatAndRunByThread(threadCount);
        }if(threadType==ThreadType.RUNNABLE){
            creatAndRunByRunable(threadCount);
        }else{
            creatAndRunByCallable();
        }
    }
    public void creatAndRunByCallable(){

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new NormalCallable());
        ExecutorService executor=Executors.newSingleThreadExecutor();
        executor.execute(futureTask);
        executor.shutdown();
        //new Thread(futureTask).start();
        try {
            System.out.println("return from callable:"+futureTask.get(3000L,TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            System.out.println("over!!");
        }

    }
    public   void creatAndRunByRunable(int threadCount){
        while(threadCount-->0){
            Thread thread = new Thread(new NormalRunnable(pubRes),"NormalRunnable-"+threadCount);
            thread.start();
        }
    }
    public   void creatAndRunByThread(int threadCount){
        while(threadCount-->0){
            NormalThread normalThread=  new NormalThread();
            normalThread.setName("NormalThread name-"+threadCount);
            normalThread.start();
        }
    }
}
