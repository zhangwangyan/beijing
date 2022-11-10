package com.desperado;

/**
 * 线程终止 --- 不正确的线程终止 -stop
 *              正确的线程终止 -interrupt
 */
public class Demo {
    int i = 0;
    int j = 0;
    public static void main(String[] args) {

        StopThread  thread = new StopThread();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //终止线程
        //thread.stop();
        thread.interrupt();
        while(thread.isAlive()){

        }
        thread.print();


    }
}
class StopThread extends  Thread{
    private int i=0;
    private int j=0;
    @Override
    public void run() {
        synchronized (this) {
            i++;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
        }
    }
    public  void print(){
        System.out.println("i========="+i);
        System.out.println("j========="+j);
    }
}
