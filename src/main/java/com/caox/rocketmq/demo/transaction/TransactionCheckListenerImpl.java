package com.caox.rocketmq.demo.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/15 15:38
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
    // private AtomicInteger transactionIndex = new AtomicInteger(0);

    /**
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
        System.out.println("msg =" + new String(msg.getBody().toString()));
        // return LocalTransactionState.ROLLBACK_MESSAGE;
        return LocalTransactionState.COMMIT_MESSAGE;
        // return LocalTransactionState.UNKNOW;
    }
}
