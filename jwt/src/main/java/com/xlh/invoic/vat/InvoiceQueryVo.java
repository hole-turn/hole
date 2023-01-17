package com.xlh.invoic.vat;

import cn.hutool.core.bean.BeanUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author: xielinhao
 * @title: InvoiceQueryVo
 * @projectName: cshop
 * @description:
 * @date: 10:34 2022/12/16
 */
@Data
public class InvoiceQueryVo {
    //开票终端标识
    private String kpzdbs;
    //纳税人识别号 和kpzdbs必传一个
    private String nsrsbh;
    //发票类型代码
    private String fplxdm;
    //查询方式
    // 0：按发票号码查询
    //1：按请求流水号查询
    private String cxfs;
    //查询条件
    //cxfs为 0时：
    //发票代码+发票号码
    //cxfs为 1时：
    //发票交易流水号
    private String cxtj;

    public Map<String, Object> toMap() {
        return BeanUtil.beanToMap(this);
    }


}
