package com.desperado.cas;

public class Counter {

    volatile  int i =0;
    public synchronized  void add(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0;j<10000;j++){
                        counter.add();
                    }
                }
            }).start();
        }
        Thread.sleep(6000L);
        System.out.println(counter.i);
    }
}
