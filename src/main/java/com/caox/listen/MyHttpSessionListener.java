package com.caox.listen;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/7 9:47
 */
@Slf4j
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("MyHttpSessionListener--------sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("MyHttpSessionListener--------sessionDestroyed");
    }
}
