package com.desperado.cas.atomic;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {
    public static void main(String[] args) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        for (int i=0;i<6;i++){

            new Thread(
                    ()->{
                        long startTime = System.currentTimeMillis();
                        while (System.currentTimeMillis()-startTime<2000){
                            longAdder.increment();
                        }
                        long endTime = System.currentTimeMillis();

                    }
            ).start();

            Thread.sleep(3000L);
            System.out.println(longAdder.sum());
        }
    }
}
