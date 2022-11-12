package com.desperado.cas;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter2 {

    volatile  int i =0;
    Lock lock=new ReentrantLock();
    public  void add(){
        lock.lock();
        try{
            i++;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter2 counter = new Counter2();
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
