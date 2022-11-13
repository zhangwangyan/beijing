package com.desperado.lock;

import com.desperado.cas.atomic.AutomicReference;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class Desperado implements Lock {
   AtomicReference<Thread> owner= new AtomicReference<>();

   LinkedBlockingQueue<Thread> waiters=new LinkedBlockingQueue<>();

    @Override
    public void lock() {
        if(!tryLock()){
         waiters.offer(Thread.currentThread());
         //用死循环防止伪唤醒
         for(;;){
          Thread head=waiters.peek();//取出队列头部，但是不出队列
           if(head==Thread.currentThread()){
                 if(tryLock()){
                     waiters.poll();
                     return;
                 }else{
                     LockSupport.park();
                 }
           }else{
               LockSupport.park();//线程挂起
           }

         }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return owner.compareAndSet(null,Thread.currentThread());
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
       if(owner.compareAndSet(Thread.currentThread(),null)){
            Thread head = waiters.peek();
            LockSupport.unpark(head);
       }


    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
