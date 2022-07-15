package com.xlh.mq6;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

import java.util.Scanner;

/**
 * @author: xielinhao
 * @title: Producer
 * @projectName: hole
 * @description:
 * @date: 17:14 2022/7/14
 */
public class Producer {
    public static final String EXCHANGE_NAME = "test";

    public static void main(String[] args) {
        try {
            Channel channel = RabbitMqUtils.getChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            Scanner sc = new Scanner(System.in);
            System.out.println("请输入信息");
            while (sc.hasNext()) {
                String message = sc.nextLine();
                String routingKey = null;
                //当发送的消息不同，路右键也不同
                if (message.equals("1")) {
                    routingKey = "1.orange.1";
                } else if (message.equals("2")) {
                    routingKey = "lazy.1.1";
                } else if (message.equals("3")) {
                    routingKey = "lazy.orange.1";
                } else if (message.equals("4")) {
                    routingKey = "orange.1";
                }
                channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }


        } catch (Exception e) {

        }
    }
}
