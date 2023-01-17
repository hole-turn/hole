package com.xlh.test.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: xielinhao
 * @title: Study2
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 11:16 2022/11/30
 */
@Data
public class Study2 {
    private BigDecimal price;

    public Study2(BigDecimal price) {
        this.price = price;
    }
}
