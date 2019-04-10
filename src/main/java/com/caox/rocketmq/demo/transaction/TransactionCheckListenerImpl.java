package com.caox.rocketmq.demo.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/15 15:38
 * 未决事务，服务器回查客户端（阉割掉了）
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
    // private AtomicInteger transactionIndex = new AtomicInteger(0);
    // 在这里，我们可以根据由MQ回传的key去数据库查询，这条数据到底是成功了还是失败了。

    /**
     * 因为RMQ在3.0.8的时候还是支持check listener回查机制的，但是到了3.2.6的时候将事务回查机制“阉割”了！
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
        System.out.println("未决事务，服务器回查客户端msg =" + new String(msg.getBody().toString()));
        // return LocalTransactionState.ROLLBACK_MESSAGE;
        return LocalTransactionState.COMMIT_MESSAGE;
        // return LocalTransactionState.UNKNOW;
    }
}
