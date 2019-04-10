package com.caox.callback.interface_callback;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/19 13:44
 * A类实现C接口
 *
 *  --------------------------------------------------------------------------------------------------------------------
 *  A类实现C接口所有的抽象方法，但不自己调用C接口中的所有的抽象方法，
 *  总会至少留一个抽象方法交给B类去调用（一般在B类中有一个未实例化的C接口对象雏形（现在还不能称之为对象，因为抽象类不能实例化，
 *  继而无法判断它需要多大内存空间，因此无法new给他合适的内存空间，因为Java不是C/C++，不允许使用类似malloc的函数去自由随意的给变量分配内存空间），
 *
 *  一般在B类中也会有一个方法去把A类new出来的那个对象赋值给B类中的那个C接口对象雏形，从而在B类不继承C接口的前提下完成对B类中C接口对象雏形的实例化，
 *  在这种前提下，B类和A类就实现了通信，而实现他们之间通信的使者，就是B类中的那个C接口的对象（对象雏形）），
 *  B类通过A类间接对C接口调用的过程就是回调，回调也是A,B类信息沟通的重要基础。
------------------------------------------------------------------------------------------------------------------------
 */
public class Boss implements Person{
    private int is_array = 0;

    @Override
    public boolean is_release() {
        if(is_array >= 3){
            System.out.println("命令已发布");
            return true;
        }
        System.out.println("您还未到达公司，不能发布命令");
        return false;
    }

    @Override
    public boolean is_checked(boolean is_finished) {
        if(is_finished){
            System.out.println("检测完成");
            return true;
        }else{
            System.out.println("员工工作尚未完成，检测失败");
        }
        return false;
    }

    public void go(){
        System.out.println("前进一公里");
        is_array++;
    }
}
