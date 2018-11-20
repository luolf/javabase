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


       final A a=test2.new A();

        // 使用方式一
//        new Thread(a).start();
//        new Thread(a).start();

        //使用方式二
        for(int i=0;i<2;i++) {
            new Thread(new Runnable() {
                public void run() {
                    a.doPrintTask();
                }
            }).start();
        }


    }
    class A implements Runnable{
        int idx=0;
        public  void run(){
            doPrintTask();

        }
        public  void doPrintTask(){
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

}
