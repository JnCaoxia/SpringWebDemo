package com.caox.rocketmq.service;

import com.alibaba.rocketmq.common.message.Message;
import com.caox.rocketmq.producer.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/14 17:27
 */
@Component("rocketService")
@Slf4j
public class RocketService {
    /**
     * 注入进来
     */
    @Autowired
    @Qualifier("rocketMQProducer")
    private RocketMQProducer rocketMQProducer;

    public void send(String topic, String tag, String msg) throws Exception{
        Message message = new Message(topic, tag, msg.getBytes("UTF-8"));
        rocketMQProducer.getDefaultMQProducer().send(message);
    }

}
