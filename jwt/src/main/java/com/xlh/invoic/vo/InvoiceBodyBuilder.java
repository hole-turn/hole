package com.xlh.invoic.vo;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author: xielinhao
 * @title: InvoiceBodyBuilder
 * @projectName: cloudShop
 * @description:
 * @date: 20:53 2022/12/14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceBodyBuilder {

    //发票行性质
    private String fphxz;
    //商品名称
    private String spmc;
    //规格型号
    private String ggxh;
    //单位
    private String dw;
    //商品数量
    private String spsl;
    //单价
    private String dj;
    //金额
    private String je;
    //税率
    private String sl;
    //税额
    private String se;
    //商品编码
    private String spbm;
    //自行编码
    private String zxbm;
    //优惠政策标识
    private String yhzcbs;
    //税率标识
    private String lslbs;
    //增值税特殊管理
    private String zzstsgl;
    //
    private Integer groupCount;

    public Map<String, Object> toMap() {
        return BeanUtil.beanToMap(this);
    }
}
