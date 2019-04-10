package com.caox.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/2/20 17:43
 */
public class SingleThreadPoolDemo {
    public static void main(String[] args) {
        // 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
        ExecutorService pool = Executors.newSingleThreadExecutor();

        Runnable task1 = new SingelTask();
        Runnable task2 = new SingelTask();
        Runnable task3 = new SingelTask();

        pool.execute(task3);
        pool.execute(task2);
        pool.execute(task1);

        // 等待已提交的任务全部结束 不再接受新的任务
        pool.shutdown();
    }
}

class SingelTask implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行… …");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }

}