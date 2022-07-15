package com.xlh.mq8;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.xlh.common.util.RabbitMqUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xielinhao
 * @title: Consumer1
 * @projectName: hole
 * @description:
 * @date: 15:55 2022/7/15
 */
public class Consumer1 {
    private final static String EXCHANGE_NAME = "test_exchange";
    private final static String NORMAL_QUEUE = "normal_queue";

    private final static String DEAD_EXCHANGE_NAME = "dead_test";
    private final static String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        Map<String, Object> params = new HashMap<>();
        //正常队列设置死信交换机 参数 key 是固定值
        params.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
        //正常队列设置死信 routing-key 参数 key 是固定值
        params.put("x-dead-letter-routing-key", "dead");
        //设置队列最大长度
        params.put("x-max-length", 6);

        channel.queueDeclare(NORMAL_QUEUE,true,false,false,params);

        channel.queueBind(NORMAL_QUEUE,EXCHANGE_NAME,"test");

        System.out.println("消费者1等待接收消息.........");
        //消息如何进行消费的业务逻辑
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("消费者1控制台接收并打印消息："+message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };

        channel.basicConsume(NORMAL_QUEUE, false, deliverCallback, cancelCallback);

    }
}
