package com.xlh.designmode.pay.celue;

import org.springframework.stereotype.Service;

/**
 * @author: xielinhao
 * @title: PayService
 * @projectName: hole
 * @description:
 * @date: 14:10 2022/8/3
 */
@Service
public class IPayService {

    public void toPay(String code){
        PayStrategyFactory.get(code).pay();
    }

}
