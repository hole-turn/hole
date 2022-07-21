package com.xlh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @author: xielinhao
 * @title: RabbitMqController
 * @projectName: hole
 * @description:
 * @date: 16:49 2022/7/18
 */
@Slf4j
@RestController
@RequestMapping("mq")
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("sendMsg/{message}")
    public void sendMessage(@PathVariable String message) {
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), message);

        rabbitTemplate.convertAndSend("x", "XA", "消息来自 ttl 为 10S 的队列:" + message);
        rabbitTemplate.convertAndSend("x", "XB", "消息来自 ttl 为 40S 的队列: " + message);
    }

    @GetMapping("sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable String message, @PathVariable String ttlTime) {

        rabbitTemplate.convertAndSend("x", "XC", message, correlationData -> {
            correlationData.getMessageProperties().setExpiration(ttlTime);
            return correlationData;
        });

        log.info("当前时间：{},发送一条时长{}毫秒 TTL 信息给队列 C:{}", new Date(), ttlTime, message);
    }

}
