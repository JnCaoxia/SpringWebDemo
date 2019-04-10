package com.test.demo;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/4 15:34
 */
public class ThreadTest implements Runnable {
    int b = 100;
    public synchronized void m1(){
        b = 10000;
        try {
            Thread. sleep(5000);
            System. out.println( "m1----b="+ b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void m2(){
        System. out.println( "m2====="+ b);
    }

    @Override
    public void run() {
        m1();
    }

    public static void main(String[] args) {
        ThreadTest tt = new ThreadTest();
        Thread t = new Thread(tt);
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tt.m2();
    }
}
