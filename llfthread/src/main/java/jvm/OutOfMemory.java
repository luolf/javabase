package jvm;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luolifeng
 * Date: 2019-02-19
 * Time: 14:25
 */
public class OutOfMemory {


        public static   void leonardoda(int a) {
            int pre2=0,pre1=0,rst=0;

            for(int i=1;i<=a;i++){
                if(i<3){
                    pre2=1;
                    pre1=1;
                    rst=1;
                }else {
                    rst = pre2 + pre1;
                    pre2 = pre1;
                    pre1 = rst;
                }
                System.out.print(rst+"  ");

            }
        }


    public static void main(String[] args){
//        System.out.print(s+"  ");
         OutOfMemory.leonardoda(11) ;
    }
}
