package com.desperado.concurrent_utils;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class DesperadoCuoutDownLauch {

    private Sync sync;

    public DesperadoCuoutDownLauch(int count) {
        this.sync=new Sync(count);
    }

    //释放共享锁
    //state初始值为count,只有在state减为0的时候，才能释放锁成功
    public void countDown(){
       sync.releaseShared(1);
    }

    //获取共享，只有在sate=0,才能获取锁成功
    public void await(){
        sync.acquireShared(1);
    }




    class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return getState()==0?1:-1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
          for (;;){
              int c=getState();
              if(c==0){
                  return false;
              }
              int nextc=c-1;
              if(compareAndSetState(c,nextc)){
                  return nextc==0;
              }
          }
        }
    }
}
