package com.xlh.invoic.vo;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author: xielinhao
 * @title: InvoiceBuilder
 * @projectName: cloudShop
 * @description:
 * @date: 15:25 2022/12/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceBuilder {
    //开票终端标识
    private String kpzdbs;
    //税控盘编号
    private String jqbh;
    //发票请求流水号
    private String fpqqlsh;
    //销方纳税人识别号
    private String shnsrsbh;
    //销货单位地址电话
    private String xhdwdzdh;
    //销货单位银行账号
    private String xhdwyhzh;
    //发票类型代码
    private String fplxdm;
    //开票类型
    private String kplx;
    //用户类型
    private String yhlx;
    //特殊票种
    private String tspz;
    //购货单位识别号
    private String ghdwsbh;
    //购货单位名称
    private String ghdwmc;
    //购货单位地址电话
    private String ghdwdzdh;
    //购货单位银行帐号
    private String ghdwyhzh;
    //收款人
    private String skr;
    //备注
    private String bz;
    //复核人
    private String fhr;
    //开票人
    private String kpr;
    //原发票代码
    private String yfpdm;
    //原发票号码
    private String yfphm;
    //收票人手机号
    private String sprsjh;
    //收票人手机号
    private String notify_url;
    //
    private Integer fyxmCount;
    //
    private String groups;

    public Map<String, Object> toMap() {
        return BeanUtil.beanToMap(this);
    }
}
