package com.xlh.test.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: xielinhao
 * @title: Stocks
 * @projectName: holeturn
 * @description:
 * @date: 15:26 2022/4/25
 */
@AllArgsConstructor
@Data
@Builder
public class Stocks {

    @ApiModelProperty(value = "批次号")
    private String batchNumIn;//批次号

    @ApiModelProperty(value = "批号")
    private String batchNum;//批号

    @ApiModelProperty(value = "库存")
    private BigDecimal overallPackNum;
}
