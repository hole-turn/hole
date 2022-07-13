package com.xlh.mq1.consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.xlh.common.util.RabbitMqUtils;

/**
 * @author: xielinhao
 * @title: Consumer2
 * @projectName: holeturn
 * @description:
 * @date: 17:10 2022/6/23
 */
public class Consumer2 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("等待接收消息.........");

        //消息如何进行消费的业务逻辑

        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(message);
        };

        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断");
        };
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费成功的回调
         * 4.消费者未成功消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, callback, cancelCallback);
    }
}
