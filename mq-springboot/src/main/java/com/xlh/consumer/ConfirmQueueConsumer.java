package com.xlh.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: xielinhao
 * @title: ConfrimQueueConfig
 * @projectName: hole
 * @description:
 * @date: 16:02 2022/7/21
 */
@Component
@Slf4j
public class ConfirmQueueConsumer {

    @RabbitListener(queues = "confirm.queue")
    public void receive(Message message) {
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到队列confirm.queue的消息：{}", new Date().toString(), msg);
    }

    @RabbitListener(queues = "warning.queue")
    public void receiveWarning(Message message) {
        String msg = new String(message.getBody());
        log.info("收到警告队列warning.queue的消息：{}", msg);
    }
}
