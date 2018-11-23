package thread.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * Description:反射的方式获取Unsafe进行测试，
 * User: luolifeng
 * Date: 2018-11-23
 * Time: 15:18
 */
public class UnsafeUtil {
    private static AtomicReference<Unsafe> instance=new AtomicReference();
    public static Unsafe getUnsafe(){
       while(true) {
           Unsafe unsafe = instance.get();
           if (unsafe != null) {
               return unsafe;
           }
           Field f = null;
           try {
               f = Unsafe.class.getDeclaredField("theUnsafe");
               f.setAccessible(true);
               unsafe = (Unsafe)f.get(null);
               if (instance.compareAndSet(null, unsafe)) {
                   return unsafe;
               }
           } catch (NoSuchFieldException e) {
               e.printStackTrace();
           } catch (IllegalAccessException e) {
               e.printStackTrace();

           }


       }
    }

    public static void main(String[] args){
        final Unsafe unsafe = UnsafeUtil.getUnsafe();

        long time = System.currentTimeMillis()+1000;

        unsafe.unpark(Thread.currentThread());
        unsafe.unpark(Thread.currentThread());
        //绝对时间后面的参数单位是毫秒
        unsafe.park(true, time);

        System.out.println("SUCCESS!!!");
        Thread[] threads=new Thread[3];
        for(int j=0;j<threads.length;j++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 3; i++) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        if (i == 1)
                            unsafe.park(false, 0);
                    }
                }
            });
            threads[j]=t;
            t.start();

        }
        try {
            for(int j=0;j<threads.length;j++) {
                System.out.println(Thread.currentThread().getName() + ":3秒后unpark"+threads[j].getName());
                Thread.sleep(3000);
                unsafe.unpark(threads[j]);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        unsafe.unpark(Thread.currentThread());
        unsafe.park(false,0);

    }
}
