package com.xlh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: xielinhao
 * @title: CloudNacosProviderApplication
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 14:20 2022/10/21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudNacosProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudNacosProviderApplication.class, args);
    }
}
