package com.desperado.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

public class CallableTest {
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        //使用：用来包裹一个callab实例，得到的futureTask实例可以传入Thread()
        CallableTask task = new CallableTask();
        DesperadoFutureTask<String> future = new DesperadoFutureTask<>(task);

        new Thread(future).start();

        String result =  future.get();      //get方法会阻塞
        System.out.println(result);


        //一个futureTask实例，只能使用一次
        //同时说明，这个任务，从头到尾只会被一个线程执行
        new Thread(future).start();





    }
}



class CallableTask implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println(">>>执行任务。。。");

        //模拟耗时
        LockSupport.parkNanos(1000 * 1000 *1000 * 5L);
        return "success";
    }
}
