package com.desperado.cas.atomic;

import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorTest {
    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator(
                (x,y)->{
                    System.out.println("x="+x);
                    System.out.println("y="+y);
                    return  x+y;
                },9L
        );

        for (int i=0;i<3;i++){
            longAccumulator.accumulate(2);
        }

        System.out.println(longAccumulator.get());
    }
}
