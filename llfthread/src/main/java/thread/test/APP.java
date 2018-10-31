package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-10-31
 * Time: 17:02
 */
public class APP {
    public static void main(String[] args){

        new APP(5,ThreadType.THREAD).creatAndRun();
        new APP(5,ThreadType.RUNNABLE).creatAndRun();
    }
    private int threadCount=10;

    private ThreadType threadType=ThreadType.THREAD;
    public APP(){}
    public APP(int threadCount,ThreadType threadType){
        this.threadCount=threadCount;
        this.threadType=threadType;
    }
    public void creatAndRun(){

        if(threadType==ThreadType.THREAD){
            creatAndRunByThread(threadCount);
        }else{
            creatAndRunByRunable(threadCount);
        }
    }
    public   void creatAndRunByRunable(int threadCount){
        while(threadCount-->0){
            Thread thread = new Thread(new NormalRunnable(),"NormalRunnable name-"+threadCount);
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
