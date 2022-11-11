package com.desperado;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 共享变量
 */
public class Demo3 {
    public static  String content="空";
    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                content="当前时间"+String.valueOf(System.currentTimeMillis());
            }
        }).start();
        new Thread(()->{
            while(true){
                System.out.println(content);
            }
        }).start();
    }
}
