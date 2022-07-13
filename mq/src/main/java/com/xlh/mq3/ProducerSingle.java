package com.xlh.mq3;

import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

/**
 * @author: xielinhao
 * @title: ProducerSingle
 * @projectName: hole
 * @description:
 * @date: 16:03 2022/7/13
 */
public class ProducerSingle {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        try {
            Channel channel = RabbitMqUtils.getChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //开启发布确认
            channel.confirmSelect();
            //开始时间
            long begin = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                String message = i + "";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                //确认是否发送成功，服务端返回 false 或超时时间内未返回，生产者可以消息重发
                boolean flag = channel.waitForConfirms();
                if (flag) {
                    System.out.println("消息发送成功");
                }
            }
            long end = System.currentTimeMillis();

            System.out.println("耗时" + (end - begin) + "ms");

        } catch (Exception e) {

        }
    }
}
