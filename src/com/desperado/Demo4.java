package com.desperado;

import java.util.concurrent.locks.LockSupport;

/**
 * jdk中对于需要多线程写作完成某一任务的场景，提供了对应API支持。
 * 多线程写作的典型场景是：生产者-消费者模型。（线程阻塞、线程唤醒）
 *
 * 场景： 线程1去买包子，没有包子，则不再执行。
 *       线程2生成包子，通知线程1继续执行。
 *
 *
 * API: suspend  resume
 *      suspend挂起目标线程，通过resume恢复线程执行。
 *
 * 被弃用的主要原因是，容易写出死锁代码。  同步代码块  执行顺序
 *     所以用wait/notify， park和unpark机制对它进行替代
 *
 *  wait/notify机制
 *     这些方法只能由同一对象锁的持有者线程调用，也就是写在同步块厘米阿尼，否则会抛出
 *     IllegalMonitorStateException异常
 *  wait方法导致当前线程等待，加入该对象的等待集合中，并且放弃当前持有的对象锁。
 *  notify/notifyAll方法唤醒一个或所有正在等待这个对象锁的喜爱昵称。
 *
 *  注意：虽然会wait解锁，但是堆顺序有要求
 *
 *
 *
 *
 *
 */
public class Demo4 {
   public static Object baozi=null;

//    public static void main(String[] args) throws InterruptedException {
//        Thread  consumer = new Thread(() -> {
//            if (baozi == null) {
//                Thread.currentThread().suspend();
//            }
//            System.out.println("买到包子，回家");
//        });
//        consumer.start();
//        Thread.sleep(3000L);
//        baozi=new Object();
//        consumer.resume();
//    }
public void test (){
    new Thread(()->{
        if(baozi==null){
            synchronized (this){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    });
}

    public static void main(String[] args) {
        LockSupport.park();
        LockSupport.unpark(Thread.currentThread());
    }

}
