package com.test.demo;

import com.alibaba.fastjson.JSON;
import com.caox.utils.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/11/6 13:46
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
public class RedisTestDemo extends AbstractJUnit4SpringContextTests {
    private static Logger logger = Logger.getLogger(RedisTest.class);
    @Autowired
    private JedisUtils jedis;

    @Test
    public void testSetGetRedis(){
        JedisUtils jedisUtils = new JedisUtils();
        String setTag = jedisUtils.set("testkey","abc");
        log.info("call set redis result :{}",setTag);
        String value = jedisUtils.get("testkey");
        log.info("call get redis result :{}",value);
    }

    @Test
    public void testDemo(){
        hmsetOrderTemplate("order:1","1","36.6","2018-01-01");
        hmsetOrderTemplate("order:2","2","38.6","2018-01-01");
        hmsetOrderTemplate("order:3","3","39.6","2018-01-01");

        jedis.lpush("user:1:order","order:1","order:2","order:3");

        hmsetOrderTemplate("order:4","4","40.6","2018-01-01");

        jedis.lpush("user:1:order", "order:4");

         List<String> orderKeys = jedis.lrange("user:1:order", 0 ,-1);
        for(String orderKey : orderKeys){
            List<String> result =  jedis.hmget(orderKey,"orderId","money","time");
            System.out.println(JSON.toJSONString(result));
        }
    }

    @Test
    public void testListDemo(){
        // 栈 先入后出 (方式一）
        Long lpushResult = jedis.lpush("user:1:info",new String[]{"a","b","c","d"});
        log.info("call lpush key: user:1:info reuslt :{}",lpushResult);
        for(int i = 0; i<lpushResult; i ++){
            String lpopResult =  jedis.lpop("user:1:info");
            System.out.println("call 栈弹出第 "+(i+1)+ "个：" + lpopResult);
        }

        //队列 先入先出 （方式一）
        Long lpushResult2 = jedis.lpush("user:11:info",new String[]{"a","b","c","d"});
        log.info("call lpush key: user:11:info reuslt :{}",lpushResult2);
        for(int i = 0; i<lpushResult2; i ++){
            String rpopResult =  jedis.rpop("user:11:info");
            System.out.println("call 队列出第 "+(i+1)+ "个：" + rpopResult);
        }

        // 栈 先入后出（方式二）
        Long rpushResult = jedis.rpush("user:2:info",new String[]{"a","b","c","d"});
        log.info("call rpush key: user:2:info reuslt :{}",rpushResult);
        for(int i = 0; i<rpushResult; i ++){
            String rpopResult =  jedis.rpop("user:2:info");
            System.out.println("call 栈弹出第 "+(i+1)+ "个：" + rpopResult);
        }

        //队列 先入先出 （方式二）
        Long rpushResult2 = jedis.rpush("user:22:info",new String[]{"a","b","c","d"});
        for(int i = 0; i<rpushResult2; i ++){
            String lpopResult =  jedis.lpop("user:22:info");
            System.out.println("call 队列出第 "+(i+1)+ "个：" + lpopResult);
        }
    }

    private void hmsetOrderTemplate(String key, String orderId, String money, String time){
        Map<String,String> orderInfo = new HashMap<>();
        orderInfo.put("orderId",orderId);
        orderInfo.put("money",money);
        orderInfo.put("time",time);
        jedis.hmset(key,orderInfo);
    }
}
