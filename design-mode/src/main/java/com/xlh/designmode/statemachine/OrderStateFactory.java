package com.xlh.designmode.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: xielinhao
 * @title: OrderStateFactory
 * @projectName: hole
 * @description:
 * @date: 11:15 2022/10/10
 */
@Component
public class OrderStateFactory {
    /**
     * 自定义bean名字前缀
     */
    public static final String ORDER_STATUS = "orderStatus";
    /**
     * 注入到容器里面
     */
    @Autowired
    Map<String, OrderState> states = new ConcurrentHashMap<>(4);

    /**
     * 获取对应状态的执行类
     *
     * @param status 状态
     * @return 具体状态的执行类
     */
    public OrderState getState(Integer status) {
        if (states.containsKey(ORDER_STATUS + status)) {
            return states.get(ORDER_STATUS + status);
        }
        throw new RuntimeException("未找到执行状态的类");
    }
}