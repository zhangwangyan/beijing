package com.desperado.lock;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InsteadOfHashTable {
    private HashMap<Object,Object> map=new HashMap<>();
   ReadWriteLock rwLock= new ReentrantReadWriteLock();

   public Object get(Object key){
       rwLock.readLock().lock();
       try{
          return   map.get(key);
       }finally {
           rwLock.readLock().unlock();
       }
   }
   public void put(Object key,Object value){
       rwLock.writeLock().lock();
       try{
           map.put(key,value);
       }finally {
           rwLock.writeLock().unlock();
       }
   }
}
