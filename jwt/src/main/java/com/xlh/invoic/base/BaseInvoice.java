package com.xlh.invoic.base;

import cn.hutool.core.util.ReflectUtil;
import com.xlh.invoic.config.InvoiceConfig;

/**
 * @author: xielinhao
 * @title: BaseInvoic
 * @projectName: cloudShop
 * @description:
 * @date: 15:22 2022/12/14
 */
public abstract class BaseInvoice<T> {

    public static <R extends BaseInvoice> R initInvoice(InvoiceConfig invoiceConfig, Class<R> clazz){
        return ReflectUtil.newInstance(clazz, invoiceConfig);
    }

    /**
     * 开票...
     * 如后续增加发票类型 继承BaseInvoice就行
     * @param t
     * @return
     */
    public abstract void invoice(T t);

    public abstract String getToken();



}
