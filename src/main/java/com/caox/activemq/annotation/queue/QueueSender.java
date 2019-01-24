//package com.caox.activemq.annotation.queue;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.stereotype.Component;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Session;
//
///**
// * @author : nazi
// * @version : 1.0
// * @date : 2019/1/11 14:28
// */
//@Component("queueSender")
//@Slf4j
//public class QueueSender {
//    @Autowired
//    @Qualifier("jmsQueueTemplate")
//    private JmsTemplate jmsTemplate;
//
//    public void send(String queueName, final String message){
//        log.info("call 发送 activemq queue 消息 | queueName：{} message:{}",queueName, message);
//        jmsTemplate.send(queueName, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(message);
//            }
//        });
//    }
//}
