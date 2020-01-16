package com.caox.service;

import com.caox.dao.IQueryDao;
import com.caox.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/5/28 13:55
 */
@Slf4j
@Service
public class QueryService {
    @Resource
    private IQueryDao queryDao;

    public List<Address> queryAddress(Address address){
        return queryDao.queryAddress(address);
    }

}
