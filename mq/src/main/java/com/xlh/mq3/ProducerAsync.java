package com.xlh.mq3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.xlh.common.util.RabbitMqUtils;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author: xielinhao
 * @title: ProducerAsync
 * @projectName: hole
 * @description:
 * @date: 16:59 2022/7/13
 */
public class ProducerAsync {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        try {
            Channel channel = RabbitMqUtils.getChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //开启发布确认
            channel.confirmSelect();

            /**
             * 用于回调函数确认发布的哈希表，线程安全，适用于并发情况
             * 1.可以将序列号和消息进行关联
             * 2.可以批量删除已经确认的消息
             * 3.支持并发访问
             */
            ConcurrentSkipListMap<Long, String> confirmsMap = new ConcurrentSkipListMap<>();

            /**
             * 确认收到消息的一个回调
             * 1.消息序列号
             * 2.true 可以确认小于等于当前序列号的消息
             *   false 确认当前序列号消息
             */
            ConfirmCallback askCallback = (sequenceNumber, multiple) -> {
                if (multiple) {
                    //把小于当前序列号的全部消息取出
                    //返回的是小于等于当前序列号的未确认消息 是一个 map
                    ConcurrentNavigableMap<Long, String> confirmed = confirmsMap.headMap(sequenceNumber, true);
                    //清除该部分未确认消息
                    confirmed.clear();
                } else {
                    //只清除当前序列号的消息
                    confirmsMap.remove(sequenceNumber);
                }
            };

            //未被确认的回调
            ConfirmCallback nackCallback = (sequenceNumber, multiple) -> {
                String message = confirmsMap.get(sequenceNumber);
                System.out.println("发布的消息" + message + "未被确认，序列号" + sequenceNumber);
            };

            /**
             * 添加一个异步确认的监听器
             * 1.确认收到消息的回调
             * 2.未收到消息的回调
             */
            channel.addConfirmListener(askCallback, nackCallback);
            //发送开始时间
            long begin = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                String message = i + "";
                //在map里面设置消息id和内容
                confirmsMap.put(channel.getNextPublishSeqNo(), message);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
            //发送结束时间
            long end = System.currentTimeMillis();
            System.out.println("发布" + 1000 + "个单独确认消息,耗时" + (end - begin) + "ms");

        } catch (Exception e) {
        }
    }
}
