package com.xlh.test.model;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author: xielinhao
 * @title: Study
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 11:16 2022/11/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder    
public class Study {
    private BigDecimal price;
    public Study toS(Study2 study2){
        BeanUtil.copyProperties(study2,this);
       return this;
    }
}
