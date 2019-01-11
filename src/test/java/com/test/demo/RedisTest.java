package com.test.demo;

import com.caox.activemq.annotation.queue.QueueSender;
import com.caox.activemq.annotation.topic.TopicSender;
import com.caox.dao.DemoDao;
import com.caox.dao.IUserDao;
import com.caox.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2018/12/28 10:27
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
public class RedisTest extends AbstractJUnit4SpringContextTests {

    private static Logger logger = Logger.getLogger(RedisTest.class);
    @Autowired
    private IUserDao userDao;

    @Autowired
    private DemoDao demoDao;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private TopicSender topicSender;

    @Test
    public void testDB(){
        StringBuilder sb = new StringBuilder();
        List<Map<String,Object>> arr = demoDao.getAddressAll();
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
        System.out.println(sb);
        log.info("call testDB : {}",sb);
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setId("402891815170e8de015170f6520b0000");
        user.setUserName("zhangsan");
        boolean res = userDao.save(user);
        Assert.assertTrue(res);
        log.info("call testSaveUser :{}", res);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user = userDao.find("402891815170e8de015170f6520b0000");
        System.out.println("testGetUser:" + user.getId() + "-" + user.getUserName() );
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId("402891815170e8de015170f6520b0000");
        user.setUserName("lisi");
        boolean res = userDao.update(user);
        Assert.assertTrue(res);
    }

    @Test
    public void testDeleteUser() {
        boolean res = userDao.delete("402891815170e8de015170f6520b0000");
        Assert.assertTrue(res);
        log.info("call testDeleteUser :{}", res);
    }

    @Test
    public void test2(){
//        String requestUrl = "/index/";
        String requestUrl = "/task/processDiagramTrace/441829821983404487374db7fc9c96b6";
//        String requestUrl = "/task/getProcessTaskToDoPage";

        String requestUrlsByGet = "/task/processDiagramTrace/";
        String compareParamUrl = requestUrl.split("/")[requestUrl.split("/").length -1];

        String ignoreMenuList = "/task/processDiagramTrace/,/abc/url,/task/getProcessTaskToDoPage";
        String compareParamUrlPre = requestUrl.split(compareParamUrl)[0];
        if(!("/".equals(compareParamUrlPre)) && Arrays.asList(requestUrlsByGet.trim().split(",")).contains(compareParamUrlPre)){
            requestUrl = compareParamUrlPre;
        }
        log.info("call compareParamUrl :{}", compareParamUrl);
        log.info("call requestUrl:{}",requestUrl);
        log.info("call ignoreMenuList :{}", Arrays.asList(ignoreMenuList.split(",")));
        log.info("call compareParamUrl :{}", Arrays.asList(ignoreMenuList.trim().split(",")).contains(requestUrl.trim()));
        log.info("call compareParamUrlPre :{}", compareParamUrlPre);
    }

    @Test
    public void testActiveMq(){
        String queueName = "test.queue";
        String message = "hello ugly girl twice!";

        queueSender.send(queueName, message);
        topicSender.send("test2.topic",message);
    }
}
