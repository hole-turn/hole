package com.xlh.designmode.statemachine;

/**
 * @author: xielinhao
 * @title: OrderStateCommon
 * @projectName: hole
 * @description:
 * @date: 11:14 2022/10/10
 */
public class OrderStateCommon implements OrderState {
    @Override
    public void handle(Order newOrder, Order oldOrder) {

    }

    /**
     * 生成日志
     *
     * @param order 订单
     */
    public void buildOrderLog(Order order) {
        //保存日志
        System.out.println("保存日志订单状态: " + order.getStatus());
    }
}