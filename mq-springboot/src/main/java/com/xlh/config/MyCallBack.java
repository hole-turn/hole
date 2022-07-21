package com.xlh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: xielinhao
 * @title: MyCallBack
 * @projectName: hole
 * @description:
 * @date: 16:00 2022/7/21
 */
@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback{

    /**
     * 交换机是否收到消息的回调方法
     * CorrelationData 消息相关数据
     * ack 交换机是否收到消息
     * cause 交换机未收到消息的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("交换机已经收到 id 为:{}的消息", correlationData.getId());
        } else {
            log.info("交换机还未收到 id 为:{}消息,由于原因:{}", correlationData.getId(), cause);
        }

    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error(" 消 息 {}, 被 交 换 机 {} 退 回 ， 退 回 原 因 :{}, 路 由 key:{}",
                new String(returned.getMessage().getBody()),
                returned.getExchange(),
                returned.getReplyText(),
                returned.getRoutingKey());
    }
}
