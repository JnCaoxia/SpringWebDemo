package com.caox.callback.async;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/19 11:05
 * 这个就是小李啦
 */
public class Li {
    /**
     * 相当于B类有参数为CallBack callBack的f()---->背景三
     * class B有一个参数为callback的方法f(CallBack callback) ——背景3
     * @param callBack
     * @param question  小王问的问题
     */
    public void executeMessage(CallBack callBack, String question){
        System.out.println("小王问的问题--->" + question);

        //模拟小李办自己的事情需要很长时间
        for(int i=0; i<10000;i++){

        }

        /**
         * 小李办完自己的事情之后想到了答案是2
         */
        String result = "答案是2";

        /**
         * 于是就打电话告诉小王，调用小王中的方法
         * 这就相当于B类反过来调用A的方法D
         * 然后b就可以在f(CallBack callback)方法中调用A的方法 ——B类调用A类的某个方法D
         */
        callBack.solve(result);

    }
}
