package com.xlh.mq9;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.xlh.common.util.RabbitMqUtils;

/**
 * @author: xielinhao
 * @title: Consumer2
 * @projectName: hole
 * @description:
 * @date: 16:29 2022/7/15
 */
public class Consumer2 {

    private final static String DEAD_EXCHANGE_NAME = "dead_test";
    private final static String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(DEAD_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        channel.queueDeclare(DEAD_QUEUE, true, false, false, null);
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE_NAME, "dead");
        System.out.println("死信队列等待接收消息.........");

        //消息如何进行消费的业务逻辑
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("死信队列控制台接收并打印消息："+message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("死信消息消费被中断");
        };

        channel.basicConsume(DEAD_QUEUE, false, deliverCallback, cancelCallback);

    }
}
