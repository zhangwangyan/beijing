package com.desperado.cas.atomic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

public class AutomicReference {
    public static void main(String[] args) {
//        Thread thread=null;
//
//        if(thread==null){
//            thread=Thread.currentThread();
//        }

        AtomicReference<Thread> own = new AtomicReference<>();
        own.compareAndSet(null, Thread.currentThread());

    }
}
