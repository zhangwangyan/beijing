package com.desperado;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 为什么要用线程池
 *
 *
 */
public class Demo5 {
    public static void main(String[] args) throws Exception{

        //核心线程池 5  最大数量10   存活时间5秒   无界队列
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i=0;i<15;i++){int n=i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("====开始执行"+n);
                        Thread.sleep(3000L);
                        System.out.println("！！！！！执行结束"+n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPoolExecutor.getPoolSize();//当前线程池线程的数量
        threadPoolExecutor.getQueue().size();//当前线程池等待的数量

        System.out.println("当前线程池数量为"+  threadPoolExecutor.getPoolSize());

        System.out.println("当前线程池等待的数量"+ threadPoolExecutor.getQueue().size());;

        Thread.sleep(15000L);
        System.out.println("当前线程池数量为"+  threadPoolExecutor.getPoolSize());

        System.out.println("当前线程池等待的数量"+ threadPoolExecutor.getQueue().size());;

    }
}
