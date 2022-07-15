package com.xlh.mq6;

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
 * @date: 14:01 2022/7/14
 */
public class Consumer2 {
    public static final String EXCHANGE_NAME = "test";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        /**
         * 生成一个临时的队列 队列的名称是随机的
         * 当消费者断开和该队列的连接时 队列自动删除
         */
        String queueName = channel.queueDeclare().getQueue();
        //交换机和队列绑定
        channel.queueBind(queueName, EXCHANGE_NAME, "lazy.#");
        System.out.println("消费者2等待接收消息.........");
        //消息如何进行消费的业务逻辑
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("消费者2控制台接收并打印消息：" + message);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };

        channel.basicConsume(queueName, true, deliverCallback, cancelCallback);

    }
}
