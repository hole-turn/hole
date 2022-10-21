package com.xlh.designmode.statemachine;

/**
 * @author: xielinhao
 * @title: OrderState
 * @projectName: hole
 * @description:
 * @date: 11:12 2022/10/10
 */
public interface OrderState {
    /**
     * 原来状态
     * @param newOrder 新订单
     * @param oldOrder 老订单
     */
    void handle(Order newOrder,Order oldOrder);
}