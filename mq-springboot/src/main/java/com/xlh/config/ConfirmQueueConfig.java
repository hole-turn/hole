package com.xlh.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author: xielinhao
 * @title: ConfrimQueueConfig
 * @projectName: hole
 * @description:
 * @date: 16:02 2022/7/21
 */
@Configuration
public class ConfirmQueueConfig {

    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";
    public static final String BACKUP_EXCHANGE_NAME = "backup.exchange";
    public static final String BACKUP_QUEUE_NAME = "backup.queue";
    public static final String WARNING_QUEUE_NAME = "warning.queue";

    @Autowired
    private MyCallBack myCallBack;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //依赖注入 rabbitTemplate 之后再设置它的回调对象
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(myCallBack);
        /**
         * true：
         * 交换机无法将消息进行路由时，会将该消息返回给生产者
         * false：
         * 如果发现消息无法进行路由，则直接丢弃
         */
        rabbitTemplate.setMandatory(true);
        //设置回退消息交给谁处理
        rabbitTemplate.setReturnsCallback(myCallBack);
    }

    @Bean
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

//    @Bean
//    public DirectExchange confirmExchange() {
//        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
//    }

    @Bean
    public Binding queueBinding(@Qualifier("confirmQueue") Queue queue,
                                @Qualifier("confirmExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("key1");
    }


    //声明备份交换机
    @Bean
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    //声明确认 Exchange 交换机的备份交换机
    @Bean
    public DirectExchange confirmExchange() {
        //设置该交换机的备份交换机
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME)
                .durable(true)
                .withArgument("alternate-exchange", BACKUP_EXCHANGE_NAME)
                .build();
    }

    //声明警告队列
    @Bean
    public Queue warningQueue() {
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    //声明备份队列
    @Bean
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    //声明警告队列绑定关系
    @Bean
    public Binding warningBind(@Qualifier("warningQueue") Queue queue,
                               @Qualifier("backupExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    //声明备份队列绑定关系
    @Bean
    public Binding backupBind(@Qualifier("backupQueue") Queue queue,
                              @Qualifier("backupExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

}
