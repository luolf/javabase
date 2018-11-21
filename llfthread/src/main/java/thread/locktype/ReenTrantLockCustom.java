package thread.locktype;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * Description: 在自旋锁基础上加入重入功能，实现可重入
 * User: luolifeng
 * Date: 2018-11-21
 * Time: 10:52
 */
public class ReenTrantLockCustom {
    AtomicReference<Thread> threadAtomicReference =new AtomicReference();
    private int count=0;
    public void lock(){
        Thread current = Thread.currentThread();


        //当前线程获取过锁则计算加1并返回
        if(threadAtomicReference.get()==current) {
            count++;
//            System.out.println(String.format("%s %d ",Thread.currentThread().getName(),count ));
            return;
        }
//        System.out.println(String.format("%s = %d ",Thread.currentThread().getName(),count ));
        while(!threadAtomicReference.compareAndSet(null,current)){}
    }
    public void unlock(){
        Thread current = Thread.currentThread();
        //当前线程正持有锁，则计算器减1
        if(threadAtomicReference.get()==current) {
            count--;
            if(count<0)
                //计数器为0则尝试释放锁
            threadAtomicReference.compareAndSet(current,null);
        }


    }

    public static void main(String[] args){
        final ReenTrantLockCustom reenTrantLockCustom=new ReenTrantLockCustom();
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                public void run() {

                    reenTrantLockCustom.new MyTask().runMe();
                }
            }).start();
        }
    }
    class MyTask{
        String name="";
        public void runMe(){

                lock();
                setTaskName(String.valueOf(this.hashCode()));
                lock();
                print();
                unlock();
                unlock();


        }
        private void setTaskName(String taskName){
            this.name=taskName;
            System.out.println(String.format("%s set   name:%s",Thread.currentThread().getName(),this.name));
        }
        private void print(){
            System.out.println(String.format("%s print name:%s",Thread.currentThread().getName(),this.name));
        }
    }
}
