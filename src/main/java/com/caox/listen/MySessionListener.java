package com.caox.listen;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/7 10:30
 */
@WebListener
@Slf4j
public class MySessionListener implements HttpSessionListener {

    /**
     * 统计在线人数的变量
     */
    private int onlineNumber;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        onlineNumber++;
        httpSessionEvent.getSession().getServletContext().setAttribute("OnlinePeople", onlineNumber);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        onlineNumber--;
        httpSessionEvent.getSession().getServletContext().setAttribute("OnlinePeople", onlineNumber);
    }
}
