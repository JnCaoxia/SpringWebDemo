package com.caox.activemq.annotation.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/11 14:22
 */
public class QueueReceiver implements MessageListener {
        @Override
        public void onMessage(Message message) {
            try {
                System.out.println("QueueReceiver接收到消息："+((TextMessage)message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
