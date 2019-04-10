package com.caox.listen;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/7 9:44
 */
@Slf4j
@WebListener
public class MyServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("MyServletContextListener--------contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("MyServletContextListener--------contextDestroyed");
    }
}
