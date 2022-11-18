package com.desperado.concurrent_utils;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class DesperadoSemaphone {

    private Sync sync;


    public DesperadoSemaphone(int permits) {
        this.sync=new Sync(permits);
    }
    //获取信号量
    public void acquire(){
         sync.tryAcquireShared(1);
    }

    //释放信号量
    public void release(){
        sync.release(1);
    }

    //AQS里面有很多没有实现的方法，要使用AQS
      //创建AQS实例，重写方法
    class Sync extends AbstractQueuedSynchronizer{
        private int permits;

        public Sync(int permits) {
            this.permits = permits;
        }

        //这里不需要考虑入队列，出队列。   这些都是不带try方法实现了 ，作为了公共逻辑
        @Override
        protected int tryAcquireShared(int arg) {
            //获取锁的线程，最多不能超过n
            int c=getState();
            int nextc=c+arg;  //arg一般是1
            if(nextc<=permits){
                if(compareAndSetState(c,nextc)){
                     return 1;
                }
            }
            return -1;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //获取锁的线程，最多不能超过n
         for(;;){
             int c=getState();
             if(c==0) return false;

             int nextc=c-arg;
             if(compareAndSetState(c,nextc)){
                 return true;
             }
         }
        }

        //同样是失败，release的时候，要自旋，而acquire却没有



    }
}
