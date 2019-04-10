package com.caox.callback.interface_callback;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/19 13:47
 * B类
 */
public class Worker {

    private int work = 0;
    /** 一个未实例化的C接口对象雏形 */
    public Person person;

    public Boss bind(Boss boss){
        // 从而在B类不继承C接口的前提下完成对B类中C接口对象雏形的实例化，
        this.person = boss;
        return boss;
    }

    public void on(){
        if(person.is_release()){
            System.out.println("老班已经发布工作，可以开始了");
        }else{
            System.out.println("尚未发布工作，请等待");
        }
    }

    public void off(){
        if(is_finished() && person.is_checked(is_finished())){
            System.out.println("工作已经完成，可以下班");
        }else{
            System.out.println("工作还没完成，不允许下班");
        }
    }

    public boolean is_finished(){
        if(this.work >= 3){
            return true;
        }
        return false;
    }

    public void job(){
        work++;
        System.out.println("员工正在工作");
    }

}
