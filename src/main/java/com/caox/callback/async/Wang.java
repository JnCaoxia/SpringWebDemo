package com.caox.callback.async;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/19 11:03
 * 这个是小王
 * 实现了一个回调接口CallBack，相当于----->背景一
 * Class A实现接口CallBack callback——背景一
 */
public class Wang  implements CallBack {

    /**
     * 小李对象的引用
     * 相当于----->背景二
     * class A中包含一个class B的引用b ——背景2
     */
    private Li li;

    /**
     * 小王的构造方法，持有小李的引用
     * @param li
     */
    public Wang(Li li){
        this.li = li;
    }

    /**
     * 小王通过这个方法去问小李的问题
     * @param question  就是小王要问的问题,1 + 1 = ?
     */
    public void askQuestion(final String question){
        //这里用一个线程就是异步，
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 小王调用小李中的方法，在这里注册回调接口
                 * 这就相当于A类调用B的方法C
                 * A的对象a调用B的方法 f(CallBack callback) ——A类调用B类的某个方法 C
                 */
                li.executeMessage(Wang.this, question);
            }
        }).start();

        //小王问完问题挂掉电话就去干其他的事情了，诳街去了
        play();
    }

    public void play(){
        System.out.println("我要逛街去了");
    }

    /**
     * 小李知道答案后调用此方法告诉小王，就是所谓的小王的回调方法
     */
    @Override
    public void solve(String result) {
        System.out.println("小李告诉小王的答案是--->" + result);
    }
}
