package com.caox.SpiderData.link;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/3/22 14:01
 * 以起过滤作用；
 */
public interface LinkFilter {
    public boolean accept(String url);
}
