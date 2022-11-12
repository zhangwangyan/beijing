package com.desperado.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter4 {
    volatile  int i =0;

    private  static Unsafe unsafe=null;
    private static  long valueOffset;
    static {
         //unsafe=Unsafe.getUnsafe();
        try {
            Field  field=Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe=(Unsafe)field.get(null);

            Field iField=Counter4.class.getDeclaredField("i");
            valueOffset=unsafe.objectFieldOffset(iField);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public  void add(){
        for(;;){
            int current=unsafe.getIntVolatile(this,valueOffset);
            if( unsafe.compareAndSwapInt(this,valueOffset,current,current+1)){
               break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter4 counter = new Counter4();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0;j<10000;j++){
                        counter.add();
                    }
                }
            }).start();
        }
        Thread.sleep(6000L);
        System.out.println(counter.i);
    }
}
