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
    private PubRes(){
    }
    private static class PubResHandle{
        private static final PubRes pubRes=new PubRes();
    }
    public static PubRes getInstance(){
        return  PubResHandle.pubRes;
    }
}
