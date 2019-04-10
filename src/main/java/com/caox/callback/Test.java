package com.caox.callback;

import com.caox.callback.async.Wang;
import com.caox.callback.async.Li;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/19 11:08
 */
public class Test {
    public static void main(String[]args){
        /**
         * new 一个小李
         */
        Li li = new Li();

        /**
         * new 一个小王
         */
        Wang wang = new Wang(li);

        /**
         * 小王问小李问题
         */
        wang.askQuestion("1 + 1 = ?");
    }
}
