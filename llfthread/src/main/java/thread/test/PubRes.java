package thread.test;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2018-11-01
 * Time: 16:47
 */
public class PubRes {
    private int tickets=100;
    private volatile ArrayList<Goods> goodsList =new ArrayList<Goods>() ;
    private ReentrantLock reentrantLock;
    private Condition emptyLock ;
    private Condition fullLock ;
    private PubRes(){
        reentrantLock =new ReentrantLock(true);
        emptyLock=reentrantLock.newCondition();
        fullLock=reentrantLock.newCondition();
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

    public ArrayList<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }

    public void setReentrantLock(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    public Condition getEmptyLock() {
        return emptyLock;
    }

    public void setEmptyLock(Condition emptyLock) {
        this.emptyLock = emptyLock;
    }

    public Condition getFullLock() {
        return fullLock;
    }

    public void setFullLock(Condition fullLock) {
        this.fullLock = fullLock;
    }
}
