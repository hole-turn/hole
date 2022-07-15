package com.xlh.mq4;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

import java.util.Scanner;

/**
 * @author: xielinhao
 * @title: Producer
 * @projectName: hole
 * @description:
 * @date: 10:20 2022/7/14
 */
public class Producer {
    public static final String EXCHANGE_NAME = "test";

    public static void main(String[] args) {
        try {
            Channel channel = RabbitMqUtils.getChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                channel.basicPublish(EXCHANGE_NAME, "", null, s.getBytes());
                System.out.println("生产者发送消息" + s);
            }

        } catch (Exception e) {
        }
    }
}
