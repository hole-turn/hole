package com.xlh.designmode.pay.celue;

import com.xlh.designmode.pay.celue.IPay;
import org.springframework.stereotype.Service;

/**
 * @author: xielinhao
 * @title: WeixinPay
 * @projectName: hole
 * @description:
 * @date: 13:50 2022/8/3
 */
@Service
public class WeixinPay implements IPay {

    @Override
    public String payName() {
        return "weixin";
    }

    @Override
    public void pay() {
        System.out.println("===发起微信支付===");
    }
}
