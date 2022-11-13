package com.desperado.sync;

/**
 * 锁粗化，锁消除
 */
public class BandEliminate {

    public void test(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("a");
        buffer.append("b");
        buffer.append("c");
        buffer.append("d");
        buffer.append("e");
        buffer.append("f");
    }
    public  void test1(){
          int i=0;
          synchronized (this){
              i++;
          }
        synchronized (this){
            i--;
        }
    }
}
