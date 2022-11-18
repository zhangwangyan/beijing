package com.desperado.concurrent_utils;

import java.util.concurrent.Semaphore;

public class TestSemaphone {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 100; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //do

                    semaphore.release();
                }
            }.start();
        }

    }
}
