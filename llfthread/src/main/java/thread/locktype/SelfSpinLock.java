package thread.locktype;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 自定义个自旋锁,单纯的自旋少量线程情况下运行效率高，
 * 线程状态不发生变化；lock若调用超过两次会发生“死锁”，死循环
 * 自旋锁只是将当前线程不停地执行循环体，不进行线程状态的改变，所以响应速度更快。
 * 但当线程数不停增加时，性能下降明显，因为每个线程都需要执行，占用CPU时间。
 * 如果线程竞争不激烈，并且保持锁的时间短。适合使用自旋锁。
 * User: luolifeng
 * Date: 2018-11-21
 * Time: 10:14
 */
public class SelfSpinLock {
    AtomicReference<Thread> threadAtomicReference =new AtomicReference();
    public void lock(){
        Thread current = Thread.currentThread();
        while(!threadAtomicReference.compareAndSet(null,current)){}
    }
    public void unlock(){
        Thread current = Thread.currentThread();
        threadAtomicReference.compareAndSet(current,null);
    }

    public static void main(String[] args){
          final SelfSpinLock selfSpinLock=new SelfSpinLock();
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                public void run() {
                    selfSpinLock.new MyTask().runMe();
                }
            }).start();
        }
    }
       class MyTask{
        String name="";
        public void runMe(){
            try {
                lock();
                setTaskName(String.valueOf(this.hashCode()));
                print();
            }catch (Exception e){}
            finally{
                unlock();
            }

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
