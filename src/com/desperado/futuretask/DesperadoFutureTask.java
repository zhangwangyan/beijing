package com.desperado.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class DesperadoFutureTask<T> implements Runnable{

    public DesperadoFutureTask(Callable<T> call){
        this.call = call;
    }

    private Callable<T> call;

    T result;


    //Runner，用来实现抢执行的权限
    AtomicReference<Thread> runner = new AtomicReference<>();

    //等待队列
    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    //任务状态
    private volatile int state = NEW;

    private static final int NEW = 0;
    private static final int RUNNING = 1;
    private static final int FINISHED = 2;


    @Override
    public void run() {
        if (state != NEW ||
                !runner.compareAndSet(null, Thread.currentThread())){
            return;
        }

        state = RUNNING;
        try {
            result = call.call();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            state = FINISHED;
        }

        while (true){
            Thread th = waiters.poll();
            if (th == null){
                break;
            }
            LockSupport.unpark(th);
        }
    }

    public T get(){
        if (state != FINISHED){
            waiters.offer(Thread.currentThread());
        }

        //挂起线程
        while (state!=FINISHED){
            LockSupport.park();
        }

        return result;
    }
}
