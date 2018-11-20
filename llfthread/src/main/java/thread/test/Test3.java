package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description: 拆箱装箱测试类
 * User: luolifeng
 * Date: 2018-11-20
 * Time: 8:38
 */
public class Test3 {
    public static void main(String[] args){
        Integer a100=100;
        Integer b200=200;
        Integer c300=300;
        Integer d100=100;
        Integer e300=300;

        if(a100==d100 && a100.equals(d100)){
            System.out.println("a100=d100");
        }else{
            System.out.println("a100<>d100");
        }

        if(c300==e300){
            System.out.println("c300=e300");
        }else{
            System.out.println("c300<>e300");
        }
        if((a100+b200)== e300){
            System.out.println("(a100+b200)==e300");

        }else
        {
            System.out.println("(a100+b200)<>e300");
        }
        if((a100+b200)==c300){
            System.out.println("(a100+b200)==c300");
        }else
        {
            System.out.println("(a100+b200)<>c300");
        }
    }
}
