package com.xlh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xielinhao
 * @title: ProviderController
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 14:22 2022/10/21
 */
@RestController
public class ProviderController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable String id){
        return  "nacos registry, serverPort: " + port + "\t id" + id;
    }
}
