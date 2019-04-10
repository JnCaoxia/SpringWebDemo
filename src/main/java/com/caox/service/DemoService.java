package com.caox.service;

import com.caox.TimeOutAop.InterfaceProperty;
import com.caox.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/4/9 17:42
 */
@Component("DemoService")
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    @InterfaceProperty
    public List<Map<String,Object>> getAddressAll(){
        return demoDao.getAddressAll();
    }

    @InterfaceProperty
    public List<Map<String,Object>> getAddress(String address){
        return demoDao.getAddress(address);
    }

}
