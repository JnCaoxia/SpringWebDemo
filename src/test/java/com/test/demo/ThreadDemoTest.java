package com.test.demo;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/1 14:48
 */
public class ThreadDemoTest {
    public static Object object = new Object();
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();  // Thread-0
        Thread2 thread2 = new Thread2();  // Thread-1

        thread1.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                }
                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"调用了object.notify()");
            }
            System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
        }
    }
}
