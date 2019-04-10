package com.caox.callback.interface_callback;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/19 14:05
 */
public class TestMain {
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        Boss boss = new Boss();
        Worker worker = new Worker();

        boss = worker.bind(boss);
        boss.is_release();

        boss.go();
        worker.on();

        boss.go();
        boss.go();

        worker.on();
        worker.off();

        worker.job();
        worker.job();
        worker.job();

        worker.off();
    }
}
