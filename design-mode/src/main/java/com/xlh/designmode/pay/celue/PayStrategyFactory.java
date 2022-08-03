package com.xlh.designmode.pay.celue;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xielinhao
 * @title: PayStrategyFactory
 * @projectName: hole
 * @description:
 * @date: 14:07 2022/8/3
 */
@Component
public class PayStrategyFactory implements ApplicationContextAware {

    private static Map<String, IPay> PAY_REGISTERS = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, IPay> beansOfType = applicationContext.getBeansOfType(IPay.class);
        for (IPay iPay : beansOfType.values()) {
            PAY_REGISTERS.put(iPay.payName(), iPay);
        }

    }

    public static IPay get(String code) {
        return PAY_REGISTERS.get(code);
    }


}
