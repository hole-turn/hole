package com.xlh.invoic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xielinhao
 * @title: InvoiceConfig
 * @projectName: cloudShop
 * @description: 数族发票开具
 * @date: 15:28 2022/12/14
 */
@Data
//@Configuration
//@ConfigurationProperties(prefix = "invoice.sz")
public class InvoiceConfig {

    private String tokenUrl;

    private String appId;

    private String appScrt;

    private String kpUrl;

    private String skpbh;

    private String invoiceNotifyUrl;




}
