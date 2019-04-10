package com.caox.rocketmq.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/14 17:07
 */
@Setter
@Getter
@ToString
@Slf4j
public class RocketMQProducer {
    private DefaultMQProducer defaultMQProducer;
    private String producerGroup;
    private String namesrvAddr;
    private String instanceName;
    private int retryTimes;

    public void init() throws MQClientException {
        this.defaultMQProducer = new DefaultMQProducer(this.producerGroup);
        defaultMQProducer.setNamesrvAddr(this.namesrvAddr);
        defaultMQProducer.setInstanceName(this.instanceName);
        defaultMQProducer.setRetryTimesWhenSendFailed(this.retryTimes);
        defaultMQProducer.start();
        log.info("rocketMQ初始化生产者完成[producerGroup：" + producerGroup + "，instanceName："+ instanceName +"]");
    }

    public void destroy() {
        defaultMQProducer.shutdown();
        log.info("rocketMQ生产者[producerGroup: " + producerGroup + ",instanceName: "+ instanceName +"]已停止");
    }

    public DefaultMQProducer getDefaultMQProducer() {
        return defaultMQProducer;
    }
}
