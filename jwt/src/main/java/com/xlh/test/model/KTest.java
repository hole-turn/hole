package com.xlh.test.model;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xlh.invoic.vo.InvoiceBodyBuilder;
import com.xlh.invoic.vo.InvoiceBuilder;
import com.xlh.invoic.vo.VatGeneralInvoiceVo;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xielinhao
 * @title: K
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 21:57 2022/12/14
 */
public class KTest {
    public static final String prodappid = "f0a4b3883cf600d8ca838b7a05021b7c";
    public static final String prodappScrt = "51d1df44005fc6e4bbe335e3a41bab470c1c8776";
    public static final String prodtokenUrl = "http://openplat.dev.shuzutech.com/openplat/dev/refreshAccessToken";
    public static final String prodkpurl = "http://paymgmt.dev.shuzutech.com/invoice/business";

//    public static final String prodappid = "f1e8c443a05b9ef4629c365a2a96e9f1";
//    public static final String prodappScrt = "32b923d1a78d7aeca5c11bffbad99e6b9b9e822a";
//    public static final String prodtokenUrl = "https://dev.zhenpos.com/openplat/dev/refreshAccessToken";
//    public static final String prodkpurl = "https://paymgmt.shuzutech.com/invoice/business";


    @SneakyThrows
    public static void main(String[] args) {
        kaipiao();


    }

    private static void kaipiao() throws IOException {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("appId", prodappid);
        map.put("appScrt", prodappScrt);
        String post = HttpUtil.post(prodtokenUrl, map, 2000);
        System.out.println("数族返回tokenJson" + post);


        JSONObject retJson = (JSONObject) JSONObject.parse(post);
        JSONObject result = retJson.getJSONObject("result");
        String token = result.getString("accessToken");


        VatGeneralInvoiceVo vatGeneralInvoiceVo = new VatGeneralInvoiceVo();
        InvoiceBuilder builder = new InvoiceBuilder();
        builder.setKpzdbs("");
        builder.setJqbh("661619914111");
//        builder.setJqbh("497013594916");
        builder.setFpqqlsh(System.currentTimeMillis() + "");
        builder.setShnsrsbh("91330211MA2H46DFX5");
        builder.setXhdwdzdh("宁波市镇海区骆驼街道民和路798号 0574-86551939");
        builder.setXhdwyhzh("中国建设银行宁波市住房城市建设支行 33150198343600001272");
        builder.setFplxdm("026");
        builder.setKplx("0");
        builder.setYhlx("0");
        builder.setTspz("00");
        builder.setGhdwsbh("91330211MA2H41D624");
        builder.setGhdwmc("浙江华民软件有限公司");
        builder.setGhdwdzdh("浙江省宁波市镇海区骆驼街道民和路798号 0574-86551939");
        builder.setGhdwyhzh("中国建设银行宁波市住房城市建设支行 33150198343600001272");
        builder.setSkr("");
        builder.setBz("");
        builder.setFhr("");
        builder.setKpr("管理员");
        builder.setYfpdm("");
        builder.setYfphm("");
        builder.setSprsjh("");
        builder.setNotify_url("https://dev.91medicine.net/gateway/cshop-server/cloud/invoice/callback");
        builder.setFyxmCount(1);
        builder.setGroups("");
        vatGeneralInvoiceVo.setInvoiceBuilder(builder);
        InvoiceBodyBuilder bodyBuilder = new InvoiceBodyBuilder();
        bodyBuilder.setFphxz("0");
        bodyBuilder.setSpmc("软件服务费");
        bodyBuilder.setGgxh("件");
        bodyBuilder.setDw("");
        bodyBuilder.setSpsl("");
        bodyBuilder.setDj("");
        BigDecimal bigDecimal = new BigDecimal("10");
        bigDecimal= bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal divide = bigDecimal.divide((new BigDecimal("1").add(new BigDecimal("0.06"))), BigDecimal.ROUND_HALF_UP);
        bodyBuilder.setJe(divide.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        bodyBuilder.setSl("0.06");
        bodyBuilder.setSe(bigDecimal.subtract(divide).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        bodyBuilder.setSpbm("3040201990000000000");
//        bodyBuilder.setZxbm("11");
        bodyBuilder.setYhzcbs("0");
        bodyBuilder.setLslbs("");
        bodyBuilder.setZzstsgl("");
        bodyBuilder.setGroupCount(1);

        List<InvoiceBodyBuilder> invoiceBodyBuilderl = new ArrayList<>();
        invoiceBodyBuilderl.add(bodyBuilder);
        vatGeneralInvoiceVo.setInvoiceBodyBuilders(invoiceBodyBuilderl);
        //解析xml
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
        String xmlString = StrUtil.format(writer.toString(), invoiceBuilder.toMap());
        System.out.println(xmlString);
        LocalDateTime now = LocalDateTime.now();
        String sign = Base64.encodeBase64String(DigestUtils.md5Hex(xmlString + now.toString() + token).getBytes());
        String body = HttpUtil.createPost(prodkpurl)
                .body(xmlString)
                .header("AppId", prodappid)
                .header("SDate", now.toString())
                .header("Content-MD5", sign)
                .header("Content-Type", "application/xml")
                .execute().body();
        System.out.println(body);

        Map<String, Object> maps = XmlUtil.xmlToMap(body);
        Object o = maps.get("body");
        System.out.println(o);

    }
}
