package com.desperado.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter3 {

    AtomicInteger i=new AtomicInteger(0);
    Lock lock=new ReentrantLock();
    public  void add(){i.incrementAndGet();

    }

    public static void main(String[] args) throws InterruptedException {
        final Counter3 counter = new Counter3();
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
