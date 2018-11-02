package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-10-31
 * Time: 17:00
 */
public class NormalRunnable implements Runnable{

    private PubRes pubRes;
    public NormalRunnable(PubRes pubRes){
        this.pubRes=pubRes;
    }
    public void run() {
            this.addMe();
    }
    public   void  addMe(){
        int myTicketsNum=0;
         String name=Thread.currentThread().getName();
        synchronized(NormalRunnable.class) {
            myTicketsNum=pubRes.getTickets();
            if(myTicketsNum>0) {
                System.out.println(String.format("name is %s,ticketsNum=%d", name, myTicketsNum));
            }else{
                System.out.println(String.format("name is %s,none tickets!!", name));
            }
        }
    }
}
