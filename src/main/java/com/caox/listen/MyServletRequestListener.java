package com.caox.listen;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/7 9:43
 */
@Slf4j
@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        log.info("MyServletRequestListener--------requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        log.info("MyServletRequestListener--------requestInitialized");
    }
}
