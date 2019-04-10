package com.caox.listen;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/7 10:10
 */
@Setter
@Getter
@ToString
@Slf4j
public class User implements HttpSessionBindingListener {

    private int age;
    private String name;
    private String sex;

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        // 当session绑定我们的user的时候执行这个方法
        log.info("session将user绑定啦~");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
       // 当session解绑我们的user的时候执行这个方法
        log.info("负心的session不要我们的user了...");
    }
}
