package com.caox.rocketmq.demo.transaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/15 15:31
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("transaction_Producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);
        // 设置事务决断处理类  【服务器回查客户端Listener】
        producer.setTransactionCheckListener(transactionCheckListener);
        producer.start();

        // String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE"};
        // 本地事务的处理逻辑，相当于示例中检查Bob账户并扣钱的逻辑  【本地事务执行器】
        TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
        for (int i = 1; i <= 2; i++) {
            try {
                // 构造MSG，省略构造参数
                Message msg = new Message("TopicTransactionTest", "transaction" + i, "KEY" + i,
                        ("Hello RocketMQ " + i).getBytes());
                String arg = "arg" + i;
                // 发送消息
                SendResult sendResult = producer.sendMessageInTransaction(msg, tranExecuter, arg);
                System.out.println(sendResult);

                Thread.sleep(10);
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
        }
        producer.shutdown();
    }
}
