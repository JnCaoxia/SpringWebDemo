package com.caox.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/12 15:35
 */
public class CacheDataTest {
    static Map<Integer,Object> dataMap=new HashMap<Integer,Object>();
    /**
     * 创建读写锁的实例
     */
    static ReadWriteLock lock=new ReentrantReadWriteLock();
    static Object getData(Integer key){
        // 读取前先上锁
        lock.readLock().lock();
        Object val = null;
        try{
            val = dataMap.get(key);
            if(val == null){
                // Must release read lock before acquiring write lock
                lock.readLock().unlock();
                lock.writeLock().lock();
                try{
                    //再次判断 可能已经由其他线程写入数据
                    // 很严谨这一步。再次判断目标值,防止写锁释放后，后面获得写锁的线程再次进行取值操作
                    if(val == null){
                        // dataMap.put(key, "");//query from db
                        val = queryDataFromDB(key);
                    }
                }finally{

                    // Downgrade by acquiring read lock before releasing write lock
                    // 再次对读进行锁住，以防止写的操作，造成数据错乱
                    lock.readLock().lock();
                    // Unlock write, still hold read
                    lock.writeLock().unlock();

                    /*
					 * 先加读锁再释放写锁读作用：
					 * 防止在29行出多个线程获得写锁进行写的操作，所以在写锁还没有释放前要上读锁
					 */
                }
            }
        }finally{
            // 最后一定不要忘记释放锁
            lock.readLock().unlock();
        }
        System.out.println("get data key="+key+">val="+val);
        return val;
    }

    static Object queryDataFromDB(Integer key){
        Object val=new Random().nextInt(1000);
        dataMap.put(key, val);
        System.out.println("write into data key=" + key + " > val=" + val);
        return val;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(new Runnable(){
                @Override
                public void run() {
                getData(new Random().nextInt(5));
            }}).start();
        }
    }
}
