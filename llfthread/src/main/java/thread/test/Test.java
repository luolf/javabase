package thread.test;

import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * Description: 使用Semaphore ，两线程交替打印字符串内容
 * User: luolifeng
 * Date: 2018-11-15
 * Time: 17:26
 */
public class Test {
    String result="";
    static int idx=0;
    Semaphore semaphore=new Semaphore(1,true); //公平策略
    public Test(String result){
        this.result=result;
    }
    public static void  main(String[]  agrs){
        final String s="中华人民共和国";
        Test test=new Test(s);

        new Thread(test.new A()).start();
        new Thread(test.new A()).start();
    }
      class  A implements Runnable{
          public void run() {
              while(Test.idx<result.length()) {
                  try {
                      semaphore.acquire();
                      if(Test.idx<result.length())
                      Test.print(String.format("%s%s",Thread.currentThread().getName(),result.substring(idx,1+idx++))   );

                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }finally {
                      semaphore.release();
                  }

              }
          }
      }
        public static void print(String s){
            System.out.println(s);
        }

}
