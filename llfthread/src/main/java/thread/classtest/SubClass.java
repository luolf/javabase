package thread.classtest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2019-02-15
 * Time: 16:55
 */
public class SubClass extends MyParent {
    public void test(){
        print();
    }
    public void print(){
        System.out.println(name);
    }

}
