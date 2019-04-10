package com.caox.controller;

import com.caox.dao.DemoDao;
import com.caox.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

/**
 * Created by nazi on 2017/11/5.
 * @author nazi
 */
@RequestMapping("/first")
@Controller
public class TestController {
    @Autowired
    private DemoDao demoDao;

    @Autowired
    private DemoService demoService;

    @RequestMapping(value="/sayHello",method = RequestMethod.GET)
    public @ResponseBody String sayHello(){
        return "hello";
    }
    @RequestMapping(value="/testDao",produces="text/html;charset=UTF-8", method = RequestMethod.GET)
    public @ResponseBody String testDao(){
        StringBuilder sb = new StringBuilder();
        List<Map<String,Object>> arr = demoService.getAddressAll();
        if(arr != null){
            for(Map<String,Object> map : arr){
                sb.append("id :"+map.get("id").toString()).append("\t");
                sb.append("address :"+map.get("address").toString()).append("\t");
                sb.append("remart :"+map.get("remark").toString()).append("\t");
                sb.append("\r\n");
            }
        }else {
            sb.append("no data");
        }
        return sb.toString();
    }
}