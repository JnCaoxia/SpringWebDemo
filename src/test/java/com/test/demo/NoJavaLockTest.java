package com.test.demo;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2018/12/29 13:51
 */
@SuppressWarnings("all")
public class NoJavaLockTest {
    //不加锁
    static class Outputer {
        public void output(String name) {
            for(int i=0; i<name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final Outputer output = new Outputer();
        //线程1打印zhangsan
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true) {
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    output.output("zhangsan");
                }
            }
        }).start();

        //线程2打印lingsi
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true) {
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    output.output("lingsi");
                }
            }
        }).start();

        //线程3打印wangwu
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true) {
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    output.output("wangwu");
                }
            }
        }).start();
    }
}
