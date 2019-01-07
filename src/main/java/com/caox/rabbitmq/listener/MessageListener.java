package com.caox.rabbitmq.listener;

import com.alibaba.fastjson.JSON;
import com.caox.model.mq.ParamsDTO;
import com.caox.rabbitmq.MqManualUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 监听获取mq消息
 *
 * @author : nazi 2019/01/07
 */
@Slf4j
@Component
public class MessageListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            long startTime = System.currentTimeMillis();
            String msg = new String(message.getBody(), "UTF-8");
            log.info("mq消息队列刷新接受消息 | PARAMS：{}", msg);

            ParamsDTO paramsDTO = JSON.parseObject(msg, ParamsDTO.class);

            log.info("mq消息队列刷新接受消息 | RESULT :{}" ,paramsDTO.getMessage());

            // 手动移除消息
            MqManualUtil.manualRemoveMS(message, channel);
            log.info("mq消息队列刷新接受消息，执行成功，耗时：{}", System.currentTimeMillis()-startTime);
        } catch (Throwable e){
            log.error("mq消息队列刷新接受消息，异常信息：", e);
            // 手动移除消息
            MqManualUtil.manualRemoveMS(message, channel);
        }
    }

}
