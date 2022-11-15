package com.desperado.lock;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自己实现一个阻塞队列，只能存储n个元素
 *    put时，若队列未满，直接put
 *           若队列满，就阻塞，直到再有空间
 *    get时，若队列中有元素，则获取到元素
 *           若无元素，则等待元素
 */
public class TestCondition2 {
    private static Lock lock =new ReentrantLock();
    static Condition putCondition=lock.newCondition();
    static Condition takeCondition=lock.newCondition();

    List<Object> list=new ArrayList<>();

    private int length;
    public TestCondition2(int length){
        this.length=length;
    }
    public void put(Object o){
        lock.lock();
        try{

                if(list.size()<length){
                    list.add(o);
                    System.out.println("put"+o);
                    //唤醒
                    takeCondition.signal();
                }else{
                    putCondition.await();
                }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public Object take(){
        lock.lock();
        try{

                if(list.size()>0){
                    Object obj=  list.remove(0);
                    System.out.println("take"+obj);
                    //唤醒
                    putCondition.signal();
                    return  obj;
                }else{
                    takeCondition.await();
                }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
       return  null;
    }

    public static void main(String[] args) throws InterruptedException {
        TestCondition2 testCondition2 = new TestCondition2(2);
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <20 ; i++) {
                     testCondition2.put("x"+i);
                }
            }
        }.start();
        Thread.sleep(3000L);
        for (int i = 0; i <10 ; i++) {
            testCondition2.take();
            Thread.sleep(3000L);

        }
    }
}
