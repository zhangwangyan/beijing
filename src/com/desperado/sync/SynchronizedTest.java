package com.desperado.sync;

public class SynchronizedTest {
    public static void main(String[] args) {

    }

    public synchronized void   update(){

    }

    public void update2(){
       synchronized (this){

       }
    }

    public static synchronized  void update3(){

    }

    public void update4(){
            synchronized (SynchronizedTest.class){

            }
    }
}
