package com.xlh.invoic.vat;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.xlh.expection.BusinessException;
import com.xlh.invoic.InvoiceUtils;
import com.xlh.invoic.base.BaseInvoice;
import com.xlh.invoic.config.InvoiceConfig;
import com.xlh.invoic.vo.InvoiceBodyBuilder;
import com.xlh.invoic.vo.InvoiceBuilder;
import com.xlh.invoic.vo.VatGeneralInvoiceVo;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: xielinhao
 * @title: VatGeneralInvoice
 * @projectName: cloudShop
 * @description: 增值税普通电子发票
 * @date: 15:36 2022/12/14
 */
@Slf4j
public class VatGeneralInvoice extends BaseInvoice<VatGeneralInvoiceVo> {

    private InvoiceConfig invoiceConfig;

    public VatGeneralInvoice(InvoiceConfig invoiceConfig) {
        this.invoiceConfig = invoiceConfig;
    }

    @SneakyThrows
    @Override
    public void invoice(VatGeneralInvoiceVo vatGeneralInvoiceVo) {
        String token = this.getToken();
        if (StringUtils.isBlank(token)) {
            throw new BusinessException("增值税普通电子发票开具失败");
        }

        //解析xml
        String xmlString = parseXml(vatGeneralInvoiceVo);
        log.info("增值税普通电子发票请求报文Xml:" + xmlString);

        LocalDateTime now = LocalDateTime.now();
        String sign = Base64.encodeBase64String(DigestUtils.md5Hex(xmlString + now.toString() + token).getBytes());
        String body = HttpUtil.createPost(invoiceConfig.getKpUrl())
                .body(xmlString)
                .header("AppId", invoiceConfig.getAppId())
                .header("SDate", now.toString())
                .header("Content-MD5", sign)
                .header("Content-Type", "application/xml")
                .execute().body();


    }


    @Override
    public String getToken() {
        return InvoiceUtils.getAccessToken(invoiceConfig);
    }

    /**
     * 解析xml
     */
    private String parseXml(VatGeneralInvoiceVo vatGeneralInvoiceVo) throws IOException {
        InvoiceBuilder invoiceBuilder = vatGeneralInvoiceVo.getInvoiceBuilder();
        List<InvoiceBodyBuilder> invoiceBodyBuilders = vatGeneralInvoiceVo.getInvoiceBodyBuilders();
        StringWriter writer;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < invoiceBodyBuilders.size(); i++) {
            @Cleanup InputStream inputStream2 = new ClassPathResource("VatInvoiceBody.xml").getInputStream();
            writer = new StringWriter();
            IOUtils.copy(inputStream2, writer, StandardCharsets.UTF_8.name());
            InvoiceBodyBuilder invoiceBodyBuilder = invoiceBodyBuilders.get(i);
            invoiceBodyBuilder.setGroupCount(i + 1);
            String format = StrUtil.format(writer.toString(), invoiceBodyBuilder.toMap());
            sb.append(format);
        }
        String vatBodies = sb.toString();
        writer = new StringWriter();
        @Cleanup InputStream inputStream2 = new ClassPathResource("VatInvoice.xml").getInputStream();
        IOUtils.copy(inputStream2, writer, StandardCharsets.UTF_8.name());
        invoiceBuilder.setFyxmCount(invoiceBodyBuilders.size());
        invoiceBuilder.setGroups(vatBodies);
        String vat = StrUtil.format(writer.toString(), invoiceBuilder.toMap());
        return vat;
    }

}
