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
        // ����ز���С������
        producer.setCheckThreadPoolMinSize(2);
        // ����ز���󲢷���
        producer.setCheckThreadPoolMaxSize(2);
        // ������
        producer.setCheckRequestHoldMax(2000);
        // ����������ϴ�����  ���������ز�ͻ���Listener��
        producer.setTransactionCheckListener(transactionCheckListener);
        producer.start();

        // String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE"};
        // ��������Ĵ����߼����൱��ʾ���м��Bob�˻�����Ǯ���߼�  ����������ִ������
        TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
        for (int i = 1; i <= 2; i++) {
            try {
                // ����MSG��ʡ�Թ������
                Message msg = new Message("TopicTransactionTest", "transaction" + i, "KEY" + i,
                        ("Hello RocketMQ " + i).getBytes());
                String arg = "arg" + i;
                // ������Ϣ
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
