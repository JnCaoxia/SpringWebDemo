package com.caox.rocketmq.listener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/14 17:18
 */
@Slf4j
public class MessageListenerImpl implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        log.info(Thread.currentThread().getName()
                + " Receive New Messages: " + msgs.size()+";msg:" + msgs);


        try{
            for(MessageExt msg : msgs){
                log.info(">>>>>>"+new String(msg.getBody(),"UTF-8"));
//                int a = 1 / 0;
            }

        }catch (Exception e){
            log.info("call consumeMessage | EXCEPTION :{}",e);
            // 加入重试机制
            if(msgs.get(0).getReconsumeTimes() == 3){
                // 成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }else{
                // 重试
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }

//        for (MessageExt msg : msgs) {
//            try {
//                log.info(">>>>>>"+new String(msg.getBody(),"UTF-8"));
//            } catch (Exception e) {
//                log.info("call consumeMessage | EXCEPTION :{}",e);
//
//            }
//        }
        // 有异常抛出来，不要全捕获了，这样保证不能消费的消息下次重推，每次重新消费间隔：10s,30s,1m,2m,3m
        // 如果没有异常会认为都成功消费
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
