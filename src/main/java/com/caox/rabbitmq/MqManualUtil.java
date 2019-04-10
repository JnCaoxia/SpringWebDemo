package com.caox.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

/**
 * MQ手动工具类
 *
 * @author : nazi 2019/01/07
 */
@Slf4j
public class MqManualUtil {

    /**
     * 手动移除消息
     *
     * @param message MQ消息对象
     * @param channel MQ通道对象
     */
    public static void manualRemoveMS(Message message, Channel channel){
        try {
            long startTime = System.currentTimeMillis();
            log.info("手动移除MQ消息开始");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("手动移除MQ消息结束，耗时：{}", System.currentTimeMillis()-startTime);
        } catch (Throwable e) {
            log.error("手动移除MQ消息异常，异常信息：", e);
        }
    }

}
