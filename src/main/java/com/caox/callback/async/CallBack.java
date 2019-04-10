package com.caox.callback.async;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/19 11:01
 * 这是一个回调接口
 */
public interface CallBack {
    /**
     * 这个是小李知道答案时要调用的函数告诉小王，也就是回调函数
     * @param result 是答案
     */
    public void solve(String result);
}
