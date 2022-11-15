package com.desperado.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GetLock {
    static Lock lock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
               // boolean result = lock.tryLock();
              //  boolean result = false;
//                try {
//                    result = lock.tryLock(5, TimeUnit.SECONDS);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                lock.lock();
                String result;
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
        th.interrupt();
    }
}
