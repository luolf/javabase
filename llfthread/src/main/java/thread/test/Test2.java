package thread.test;

/**
 * Created with IntelliJ IDEA.
 * Description:使用notify wait ，两线程交替打印字符串内容
 * User: luolifeng
 * Date: 2018-11-19
 * Time: 16:10
 */
public class Test2 {
    String result="";
     int idx=0;
    public Test2(String result){
        this.result=result;
    }
    public  void print(int idx){
        System.out.println(Thread.currentThread().getName()+result.substring(idx,1+idx));
    }

    public static void main(String[] str){
        String s="中华人民共和国";
        Test2 test2=new Test2(s);
        A a=test2.new A();
        new Thread(a).start();
        new Thread(a).start();
    }
    class A implements Runnable{
        int idx=0;
        public  void run(){
            synchronized(this){
                while(idx<result.length()){
                    print(idx++);
                    try {
                        this.notify();
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
    //                    System.out.println("hee");
                    }
              }
                this.notify();
            }

        }
    }
    class B implements Runnable{
        public  void run(){

        }
    }
    class C implements Runnable{
        public  void run(){

        }
    }
}
