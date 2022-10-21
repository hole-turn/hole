package com.xlh.designmode.statemachine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xielinhao
 * @title: Order
 * @projectName: hole
 * @description:
 * @date: 11:12 2022/10/10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 金额
     */
    private BigDecimal price;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
}