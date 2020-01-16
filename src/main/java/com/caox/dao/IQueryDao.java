package com.caox.dao;

import com.caox.model.Address;

import java.util.List;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/5/28 13:57
 */
public interface IQueryDao {

    List<Address> queryAddress(Address address);
}
