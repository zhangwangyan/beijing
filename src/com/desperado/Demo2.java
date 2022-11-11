package com.desperado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件共享Demo
 */
public class Demo2 {
    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                try {
                    Files.write(Paths.get("Demo2.log"),
                            ("当前时间"+String.valueOf(System.currentTimeMillis())).getBytes(StandardCharsets.UTF_8));
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(()->{
            while(true){
                try {
                    byte[] bytes = Files.readAllBytes(Paths.get("Demo2.log"));
                    System.out.println(new String(bytes));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
