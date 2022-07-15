package com.xlh.mq7;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

import java.util.Scanner;

/**
 * @author: xielinhao
 * @title: Producer
 * @projectName: hole
 * @description:
 * @date: 15:29 2022/7/15
 */
public class Producer {

    private final static String EXCHANGE_NAME = "test_exchange";

    public static void main(String[] args) {
        try {

            Channel channel = RabbitMqUtils.getChannel();
            /**
             * 声明一个 exchange
             * 1.exchange 的名称
             * 2.exchange 的类型
             */
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            //设置消息的三十秒过期时间
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("30000").build();

            Scanner sc = new Scanner(System.in);
            System.out.println("请输入信息");
            while (sc.hasNext()) {
                String message = sc.nextLine();
                channel.basicPublish(EXCHANGE_NAME, "test", properties, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }

        } catch (Exception e) {
        }
    }
}
