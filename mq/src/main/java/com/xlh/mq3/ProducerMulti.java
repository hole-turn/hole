package com.xlh.mq3;

import com.rabbitmq.client.Channel;
import com.xlh.common.util.RabbitMqUtils;

/**
 * @author: xielinhao
 * @title: ProducerMulti
 * @projectName: hole
 * @description:
 * @date: 16:54 2022/7/13
 */
public class ProducerMulti {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        try{
            Channel channel = RabbitMqUtils.getChannel();

            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //开启发布确认
            channel.confirmSelect();

            //批量确认消息大小
            int batchSize = 100;
            //未确认消息个数
            int unConfirmMessageNum = 0;
            //开始发送时间
            long begin = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                String message = i + "";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                //发送一条消息，未确认数+1
                unConfirmMessageNum++;
                if (batchSize == unConfirmMessageNum){
                    //等待进行批量确认
                    channel.waitForConfirms();
                    unConfirmMessageNum = 0;
                }
            }
            //为了确保还有剩余没有确认消息 再次确认
            if (unConfirmMessageNum > 0) {
                channel.waitForConfirms();
            }
            //结束时间
            long end = System.currentTimeMillis();
            System.out.println("发布" + 1000 + "个单独确认消息,耗时" + (end - begin) + "ms");

        }catch (Exception e){

        }
    }
}
