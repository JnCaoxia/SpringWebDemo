package com.caox.ThreadPool;

import java.util.TimerTask;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/2/28 10:54
 */
public class OneTask extends TimerTask {

    private int id;
    public OneTask(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("线程"+ id +":  正在 执行。。");
    }
}
