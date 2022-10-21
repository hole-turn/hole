package com.xlh.designmode.statemachine;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: xielinhao
 * @title: OrderStateFor1
 * @projectName: hole
 * @description:
 * @date: 11:14 2022/10/10
 */
@Component(OrderStateFactory.ORDER_STATUS + 1)
public class OrderStateFor1 extends OrderStateCommon {
    @Override
    public void handle(Order newOrder, Order oldOrder) {
        if (Objects.isNull(oldOrder)){
            System.out.println("不做操作");
        }
        System.out.println("订单状态:1 处理逻辑==>");
        //保存日志
        super.buildOrderLog(newOrder);
    }
}