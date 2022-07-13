package com.xlh.mq2;

import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

import java.util.Scanner;

/**
 * @author: xielinhao
 * @title: Producer
 * @projectName: hole
 * @description:
 * @date: 16:58 2022/7/12
 */
public class Producer {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {

        try {
            Channel channel = RabbitMqUtils.getChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {

                String message = scanner.next();

                int prefetchCount = 1;
                channel.basicQos(prefetchCount);

                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("发送消息完成:" + message);

            }

        } catch (Exception e) {

        }
    }
}
