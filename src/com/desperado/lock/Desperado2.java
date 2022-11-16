package com.desperado.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class Desperado2 implements Lock {
   //AtomicReference<Thread> owner= new AtomicReference<>();

      Thread  owner=null;
      AtomicInteger count=new AtomicInteger(0);



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
        int ct = count.get();
        if(ct!=0){
               if(owner==Thread.currentThread()){
                   count.set(ct+1);
                   return true;
               }else{
                   return  false;
               }

        }else{
            if(count.compareAndSet(ct,ct+1)){
                owner=Thread.currentThread();
                return  true;
            }else{
                return  false;
            }
        }
        //先判断count 是否为0，不为0，被占用
        //判断是否是当前线程，若是，重入
        //若不是，进入等待队列，挂起线程

        //count=0;说明锁未被占用，CAS抢锁

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
          if(tryLock()){
              Thread head=waiters.peek();
              if(head!=null){
                  LockSupport.unpark(head);
              }
          }

    }

    public boolean tryUnLock(){
        //判断锁是否被当前线程湖区
        if(owner!=Thread.currentThread()){
            throw new IllegalMonitorStateException();
        }else{
            //若是，count-1
            int nextc = count.decrementAndGet();
            count.set(nextc);

            //若nextc为0，说明解锁成功
            if(nextc==0){
                owner=null;
                return true;
            }else {
                return false;
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
