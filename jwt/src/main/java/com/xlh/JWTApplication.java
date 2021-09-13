package com.xlh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: xielinhao
 * @title: JWTApplication
 * @projectName: hole
 * @description:
 * @date: 17:21 2021/9/9
 */
@MapperScan("com.xlh.mapper")
@SpringBootApplication
public class JWTApplication {
    public static void main(String[] args) {
        SpringApplication.run(JWTApplication.class, args);
    }
}
