package com.desperado.concurrent_utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DesperadoCyclicBarrier {

    //condition实现
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    //一个批次的大小
    private final int parties;

    //记录当前一轮有多少个线程等待
    private int count = 0;

    //全局年代
    private Object generation = new Object();



    public DesperadoCyclicBarrier(int parties){
        if (parties <=0)
            throw new IllegalArgumentException();
        this.parties = parties;
    }

    //进入下一轮等待，叫做进入下一个 年代
    public void nextGeneration(){
        count = 0;
        generation = new Object();
        condition.signalAll();
    }

    public void await(){
        lock.lock();
        try {

            Object myGeneration = generation;

            int index = ++count;
            //若当前一轮，集满
            if (index == parties){
                //进入下一轮 : count =0, 唤醒所有线程
                nextGeneration();
                return;
            }

            for (;;){
                //没有集满，挂起线程
                try {
                    condition.await();   //await方法用pak来实现的
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //什么时候应该让线程结束等待？？？
                if (myGeneration != generation)
                    return;
            }
        }finally {
            lock.unlock();
        }
    }

}
