package com.caox.rocketmq.demo.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/15 15:34
 * 执行本地事务
 */
public class TransactionExecuterImpl implements LocalTransactionExecuter {
    @Override
    public LocalTransactionState executeLocalTransactionBranch(final Message msg, final Object arg) {
        System.out.println("执行本地事务msg = " + new String(msg.getBody()));
        // 接收 arg  在这里，我们可以根据由MQ回传的key去数据库查询，这条数据到底是成功了还是失败了。
        System.out.println("执行本地事务arg = " + arg);
        String tags = msg.getTags();
        if ("transaction2".equals(tags)) {
            System.out.println("======我的操作============，失败了  -进行ROLLBACK");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
        // return LocalTransactionState.UNKNOW;
    }
}
