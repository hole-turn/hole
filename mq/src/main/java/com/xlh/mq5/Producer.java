package com.xlh.mq5;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

import java.util.Scanner;

/**
 * @author: xielinhao
 * @title: Producer
 * @projectName: hole
 * @description:
 * @date: 11:18 2022/7/14
 */
public class Producer {
    public static final String EXCHANGE_NAME = "test";

    public static void main(String[] args) {
        try {
            Channel channel = RabbitMqUtils.getChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                channel.basicPublish(EXCHANGE_NAME, "k1", null, s.getBytes());
                System.out.println("生产者发送消息" + s);
            }

        } catch (Exception e) {
        }
    }
}
