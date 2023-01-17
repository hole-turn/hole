package com.xlh.invoic.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: xielinhao
 * @title: VatGeneralInvoiceVo
 * @projectName: cloudShop
 * @description:
 * @date: 15:37 2022/12/14
 */
@Data
public class VatGeneralInvoiceVo {

    private String storeUuid;

    private InvoiceBuilder invoiceBuilder;

    private List<InvoiceBodyBuilder> invoiceBodyBuilders;
}
