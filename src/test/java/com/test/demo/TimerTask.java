package com.test.demo;

import com.caox.ThreadPool.OneTask;

import java.util.Date;
import java.util.Timer;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/2/28 10:56
 */
public class TimerTask {
    public static void main(String[] args) {

        Timer timer = new Timer();

        timer.schedule(new OneTask(1), 5000);// 5秒后启动任务

        OneTask secondTask= new OneTask(2);
        timer.schedule(secondTask, 1000, 3000);// 1秒后启动任务,以后每隔3秒执行一次线程

        Date date = new Date();
        timer.schedule(new OneTask(3),new Date(date.getTime()+1000));//以date为参数，指定某个时间点执行线程

//      timer.cancel();
//      secondTask.cancel();
        System.out.println("end in main thread...");
    }
}
