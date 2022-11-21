package com.xlh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: xielinhao
 * @title: ConsumerController
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 14:31 2022/10/21
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @GetMapping(value = "/consumer/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        return restTemplate.getForObject(serviceUrl + "/payment/nacos/" + id, String.class);
    }
}
