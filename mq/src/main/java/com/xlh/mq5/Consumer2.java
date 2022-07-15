package com.xlh.mq5;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.xlh.common.util.RabbitMqUtils;

/**
 * @author: xielinhao
 * @title: Consumer
 * @projectName: hole
 * @description:
 * @date: 11:10 2022/7/14
 */
public class Consumer2 {
    public static final String EXCHANGE_NAME = "test";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        /**
         * 生成一个临时的队列 队列的名称是随机的
         * 当消费者断开和该队列的连接时 队列自动删除
         */
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, "k2");

        System.out.println("消费者2等待接收消息");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("消费者2" + "消息" + message);
        };

        CancelCallback cancelCallback = (consumerTag -> {
            System.out.println("消息消费被中断");
        });

        channel.basicConsume(queueName, true, deliverCallback, cancelCallback);

    }
}
