package thread.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * Description:单例使用AtomicReference
 * User: luolifeng
 * Date: 2018-11-20
 * Time: 14:07
 */
public class SingletonUseAtomicReference {
    private static AtomicReference<SingletonUseAtomicReference> instance=new AtomicReference();
    private SingletonUseAtomicReference(){

    }
    public static SingletonUseAtomicReference getSingletonUseAtomicReference(){
        while(true) {
            SingletonUseAtomicReference singleton = instance.get();
            if (singleton != null) {
                System.out.println(String.format("%s获取到实例%s",Thread.currentThread().getName(),singleton));
                return singleton;
            }
            System.out.println(String.format("%s准备创建实例",Thread.currentThread().getName()));

            singleton = new SingletonUseAtomicReference();
            if (instance.compareAndSet(null, singleton)) {
                System.out.println(String.format("%s创建了实例%s",Thread.currentThread().getName(),singleton));
                return singleton;
            }

            System.out.println(String.format("%s创建实例失败，下一次先尝试获取或创建实例",Thread.currentThread().getName()));

        }
    }
    public static void main(String[] args){
        final CountDownLatch countDownLatch=new CountDownLatch(5);
        final HashSet<String> hashSet=new HashSet<String>();
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                public void run() {
                    hashSet.add(SingletonUseAtomicReference.getSingletonUseAtomicReference().toString());
//                    System.out.println(Thread.currentThread().getName()+":"+SingletonUseAtomicReference.getSingletonUseAtomicReference());
                        countDownLatch.countDown();
                }
            }).start();
        }
        System.out.println("等待其余线程完成...");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //预期结果为1
        System.out.println(String.format("其他线程都已完成，开始输出结果:%d",hashSet.size()));




    }


}
