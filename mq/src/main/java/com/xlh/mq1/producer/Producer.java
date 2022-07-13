package com.xlh.mq1.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.xlh.common.util.RabbitMqUtils;

import java.util.Scanner;

/**
 * @author: xielinhao
 * @title: Producer
 * @projectName: holeturn
 * @description:
 * @date: 16:59 2022/6/23
 */

public class Producer {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        //channel 实现了自动 close 接口 自动关闭 不需要自己进行关闭
        try {

            Channel channel = RabbitMqUtils.getChannel();
            /**
             * 声明一个队列
             * 1.队列名称
             * 2.队列里面的消息是否持久化 默认消息存储在内存中
             * 3.源码解释：(如果我们声明一个独占队列，则为 true（仅限于此连接）) 一般排外队列由消费者声明,因为消费者是持续监听的
             * 　特别注意 :如果队列是排外的，会对当前队列加锁，非同一个Connection的通道channel是不能访问的，如果强制访问会报异常reply-code=405
             * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 true 自动删除
             * 5.其他参数
             */
            boolean durable =true;//持久化
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {
                String message = scanner.next();
                /**
                 * 发送一个消息
                 * 1.发送到那个交换机
                 * 2.路由的 key 是哪个
                 * 3.其他的参数信息
                 * 4.发送消息的消息体
                 */
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, message.getBytes());//消息持久化
//                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("发送消息完成:" + message);

            }

        } catch (Exception e) {

        }

    }
}
