package com.xlh.mq9;

import com.rabbitmq.client.*;
import com.xlh.common.util.RabbitMqUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xielinhao
 * @title: Consumer1
 * @projectName: hole
 * @description:
 * @date: 16:22 2022/7/15
 */
public class Consumer1 {

    private final static String EXCHANGE_NAME = "test_exchange";
    private final static String NORMAL_QUEUE = "normal_queue";

    private final static String DEAD_EXCHANGE_NAME = "dead_test";
    private final static String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        Map<String, Object> params = new HashMap<>();
        //正常队列设置死信交换机 参数 key 是固定值
        params.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
        //正常队列设置死信 routing-key 参数 key 是固定值
        params.put("x-dead-letter-routing-key", "dead");

        channel.queueDeclare(NORMAL_QUEUE, true, false, false, params);
        channel.queueBind(NORMAL_QUEUE, EXCHANGE_NAME, "test");

        System.out.println("消费者1等待接收消息.........");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            //假设现在三位数的消息，我们都设置为消费失败，退回去 requeue为true ，如果为false，直接进入死信队列
            if (message.length() >= 3) {
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(), true);
            } else {
                System.out.println("消费者1控制台接收并打印消息："+message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };

        channel.basicConsume(NORMAL_QUEUE, false, deliverCallback, cancelCallback);
    }
}
