package com.desperado.lock;

import com.desperado.cas.Counter2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    volatile  int i =0;
    Lock lock=new Desperado2();
    public  void add(){
        lock.lock();
        try{
            i++;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
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
