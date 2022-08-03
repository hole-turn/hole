package com.xlh.designmode.controller;

import com.xlh.designmode.pay.celue.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xielinhao
 * @title: PayController
 * @projectName: hole
 * @description:
 * @date: 14:31 2022/8/3
 */
@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private IPayService payService;

    @GetMapping("/{code}")
    public void pay(@PathVariable String code){
       payService.toPay(code);
    }
}
