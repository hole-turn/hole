package com.xlh.designmode.pay.celue;

import org.springframework.stereotype.Service;

/**
 * @author: xielinhao
 * @title: AliaPay
 * @projectName: hole
 * @description:
 * @date: 13:50 2022/8/3
 */
@Service
public class AliaPay implements IPay {
    @Override
    public String payName() {
        return "alia";
    }

    @Override
    public void pay() {
        System.out.println("===发起支付宝支付===");
    }
}
