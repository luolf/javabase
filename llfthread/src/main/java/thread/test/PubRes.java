package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-01
 * Time: 16:47
 */
public class PubRes {
    private int tickets=100;
    private
    private PubRes(){
    }
    private static class PubResHandle{
        private static final PubRes pubRes=new PubRes();
    }
    public static PubRes getInstance(){
        return  PubResHandle.pubRes;
    }

    public int getTickets() {

        return tickets>0?tickets--:0;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
}
