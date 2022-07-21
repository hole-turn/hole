package com.xlh.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xielinhao
 * @title: QueueConfig
 * @projectName: hole
 * @description:
 * @date: 16:24 2022/7/18
 */
@Configuration
public class QueueConfig {

    private String xExchange = "x";
    private String queueA = "QA";
    private String queueB = "QB";

    private String yDeadLetterExchange = "Y";
    private String deadLetterQueue = "QD";

    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(xExchange);
    }

    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(yDeadLetterExchange);
    }

    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> args = new HashMap<>(3);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", yDeadLetterExchange);
        //声明当前队列的死信路由 key
        args.put("x-dead-letter-routing-key", "YD");
        //声明队列的 TTL
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(queueA).withArguments(args).build();
    }

    @Bean
    public Binding queueABindX(@Qualifier("queueA") Queue queueA,
                               @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> args = new HashMap<>(3);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", yDeadLetterExchange);
        //声明当前队列的死信路由 key
        args.put("x-dead-letter-routing-key", "YD");
        //声明队列的 TTL
        args.put("x-message-ttl", 40000);
        return QueueBuilder.durable(queueB).withArguments(args).build();
    }

    @Bean
    public Binding queueBBindX(@Qualifier("queueB") Queue queueB,
                               @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    //声明死信队列 QD
    @Bean("queueD")
    public Queue queueD() {
        return new Queue(deadLetterQueue);
    }

    //声明死信队列 QD 绑定关系
    @Bean
    public Binding deadLetterBindingQAD(@Qualifier("queueD") Queue queueD,
                                        @Qualifier("yExchange") DirectExchange yExchange) {
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }


    private String queueC = "QC";

    //声明队列 C 死信交换机
    @Bean("queueC")
    public Queue queueC(){
        Map<String, Object> args = new HashMap<>(3);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", yDeadLetterExchange);
        //声明当前队列的死信路由 key
        args.put("x-dead-letter-routing-key", "YD");
        //没有声明 TTL 属性
        return QueueBuilder.durable(queueC).withArguments(args).build();
    }

    @Bean
    public Binding queueCBindX(@Qualifier("queueC") Queue queueC,
                               @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }
}
