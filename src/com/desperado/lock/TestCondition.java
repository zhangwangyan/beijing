package com.desperado.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//Conditionsisuo 死锁和wait/notify 一样。
public class TestCondition {
    private static Lock lock =new ReentrantLock();
    static Condition condition=lock.newCondition();//相等于WaitSet

    public static void main(String[] args) {
        Thread thread=new Thread(){
            @Override
            public void run() {

                lock.lock();
                try{
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        thread.start();

        lock.lock();
        try{
            condition.signal();
        }finally {
            lock.unlock();
        }

    }

}
