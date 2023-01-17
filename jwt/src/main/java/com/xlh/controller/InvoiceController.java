package com.xlh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xielinhao
 * @title: InvoiceController
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 14:39 2022/12/15
 */
@RestController
@RequestMapping("invoice")
public class InvoiceController {

    @GetMapping("callback")
    public void callback(@RequestBody String param){
        System.out.println(param);
    }
}
