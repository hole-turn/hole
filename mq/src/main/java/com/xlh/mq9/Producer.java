package com.xlh.mq9;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

/**
 * @author: xielinhao
 * @title: Producer
 * @projectName: hole
 * @description:
 * @date: 16:30 2022/7/15
 */
public class Producer {

    private final static String EXCHANGE_NAME = "test_exchange";

    public static void main(String[] args) {
        try {

            Channel channel = RabbitMqUtils.getChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("30000").build();

            for (int i = 0; i <= 1000; i++) {
                String message = i + "";
                channel.basicPublish(EXCHANGE_NAME, "test", properties, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }

        } catch (Exception e) {

        }
    }
    //
}
