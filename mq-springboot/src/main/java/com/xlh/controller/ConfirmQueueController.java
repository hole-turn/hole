package com.xlh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xielinhao
 * @title: ConfirmQueueController
 * @projectName: hole
 * @description:
 * @date: 16:12 2022/7/21
 */
@RestController
@Slf4j
@RequestMapping("confirm")
public class ConfirmQueueController {

    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("sendMsg/{message}")
    public void sendMessage(@PathVariable String message) {
        //指定消息 id 为 1
        CorrelationData correlationData = new CorrelationData("1");

        String routingKey = "key1";
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, routingKey, message + routingKey, correlationData);

        //这个交换机不存在，会发送失败
        correlationData = new CorrelationData("2");
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME + "1", routingKey, message + routingKey, correlationData);

        //这个key2是没有交换机的key，会发送失败
        correlationData = new CorrelationData("3");
        routingKey = "key2";
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, routingKey, message + routingKey, correlationData);
        log.info("发送消息内容:{}", message);
    }
}
