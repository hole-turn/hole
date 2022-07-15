package com.xlh.mq8;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

/**
 * @author: xielinhao
 * @title: Producer
 * @projectName: hole
 * @description:
 * @date: 16:02 2022/7/15
 */
public class Producer {

    private final static String EXCHANGE_NAME = "test_exchange";

    public static void main(String[] args) {
        try {

            Channel channel = RabbitMqUtils.getChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            System.out.println("请输入信息");
            //自动发信息，发送速度比较快，可造成队列到达最大长度
            for (int i = 0; i <= 1000; i++) {
                String message = i + "";
                channel.basicPublish(EXCHANGE_NAME, "test", null, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }
        } catch (Exception e) {
        }
    }
}
