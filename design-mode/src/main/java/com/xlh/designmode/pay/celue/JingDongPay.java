package com.xlh.designmode.pay.celue;

import org.springframework.stereotype.Service;

/**
 * @author: xielinhao
 * @title: JingDongPay
 * @projectName: hole
 * @description:
 * @date: 13:51 2022/8/3
 */
@Service
public class JingDongPay implements IPay {
    @Override
    public String payName() {
        return "jingdong";
    }

    @Override
    public void pay() {
        System.out.println("===发起京东支付===");
    }
}
