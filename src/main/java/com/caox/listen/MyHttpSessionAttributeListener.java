package com.caox.listen;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/7 9:54
 */
@Slf4j
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("MyHttpSessionAttributeListener----------attributeAdded");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("MyHttpSessionAttributeListener----------attributeRemoved");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("MyHttpSessionAttributeListener----------attributeReplaced");
    }
}
