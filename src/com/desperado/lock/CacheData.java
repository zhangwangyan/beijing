package com.desperado.lock;

import org.omg.CORBA.UNSUPPORTED_POLICY;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存实例
 * 读取数据，使用数据过程中，数据不能被修改
 *
 *
 * 锁降级   写锁降级到读锁
 */
public class CacheData {
}

class MoneyInfoCache{
    //数据是否可用
    static  volatile  boolean cacheValid;

    //锁
    static  final ReadWriteLock rwl=new ReentrantReadWriteLock();

     //查询并使用数据
    static  void processCacheDaa(String dataKey){
        Object data=null;
         rwl.readLock().lock();
         try{
               //判断数据是否可用
              rwl.readLock().unlock();
              rwl.writeLock().lock();
              try{
                  if(!cacheValid){
                      //从数据库读取
                      data=DataBase.queryUserInfo();
                      Redis.data.put(dataKey,data);
                      cacheValid=true;
                  }
              }finally {
                  rwl.readLock().lock(); //锁降级
                  rwl.writeLock().unlock();

              }

         }finally {
             rwl.readLock().unlock();
         }
    }
}

class DataBase{
    static String queryUserInfo(){
        System.out.println("查询数据库。。。");
        return "name:Kody,age:40,gender:man";
    }
}
class Redis{
    static Map<String,Object> data=new HashMap<>();
}
