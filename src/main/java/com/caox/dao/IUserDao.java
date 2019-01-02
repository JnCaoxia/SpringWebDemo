package com.caox.dao;


import com.caox.model.User;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2018/12/28 10:16
 */
public interface IUserDao {

    public boolean save(User user);

    public boolean update(User user);

    public boolean delete(String userIds);

    public User find(String userId);
}
