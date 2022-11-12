package com.desperado.jmm;

/**             32位jdk       64位jdk
 *  -server     不打印i的值    不打印i的值
 *  -client     打印i的值     不打印i的值
 */
public class DemoVisibility {
    int i=0;
    public volatile  boolean isRunning =true;

    public static void main(String[] args) throws InterruptedException {
        DemoVisibility demoVisibility = new DemoVisibility();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("你好，desperado");
                        while(demoVisibility.isRunning){
                            demoVisibility.i++;
                        }
                        System.out.println(demoVisibility.i);
                    }
                }
        ).start();
        Thread.sleep(3000L);
        demoVisibility.isRunning=false;
        System.out.println("shutdown...");
    }
}
