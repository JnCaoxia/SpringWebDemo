package com.caox.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/11 14:00
 */
@SuppressWarnings("all")
public class TopicReceiver {
    /**
     * tcp 地址
     */
    public static final String BROKER_URL = "tcp://localhost:61616";
    /**
     * 目标，在ActiveMQ管理员控制台创建 http://localhost:8161/admin/queues.jsp
     */
    public static final String TARGET = "hoo.mq.topic";

    public static void run() throws Exception {
        TopicConnection connection = null;
        TopicSession session = null;
        try {
            // 创建链接工厂
            TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
            // 通过工厂创建一个连接
            connection = factory.createTopicConnection();
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Topic topic = session.createTopic(TARGET);
            // 创建消息制作者
            TopicSubscriber subscriber = session.createSubscriber(topic);

            subscriber.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {
                    if (msg != null) {
                        MapMessage map = (MapMessage) msg;
                        try {
                            System.out.println(map.getLong("time") + "接收#" + map.getString("text"));
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            // 休眠100ms再关闭
            Thread.sleep(1000 * 100);
            // 提交会话
            session.commit();

        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭释放资源
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        TopicReceiver.run();
    }
}
