package com.xlh.test;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xlh.invoic.InvoiceUtils;
import com.xlh.invoic.vat.InvoiceQueryVo;
import com.xlh.test.model.Study;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: xielinhao
 * @title: J
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 9:49 2022/11/11
 */
public class J {

    public static final String prodappid = "f1e8c443a05b9ef4629c365a2a96e9f1";
    public static final String prodappScrt = "32b923d1a78d7aeca5c11bffbad99e6b9b9e822a";
    public static final String prodtokenUrl = "https://dev.zhenpos.com/openplat/dev/refreshAccessToken";
    public static final String prodkpurl = "https://paymgmt.shuzutech.com/invoice/business";

    @SneakyThrows
    public static void main(String[] args) {
//String s ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//        "<business id=\"FPCX\">\n" +
//        "<body>\n" +
//        "\t<input>\n" +
//        "\t\t<kpzdbs></kpzdbs>\n" +
//        "     <nsrsbh>91330211MA2H46DFX5</nsrsbh>\n" +
//        "\t\t<fplxdm>026</fplxdm>\n" +
//        "\t\t<cxfs>1</cxfs>\n" +
//        "\t\t<cxtj>1673419747996</cxtj>\n" +
//        "\t</input>\n" +
//        "</body>\n" +
//        "</business>";
        InvoiceQueryVo queryVo = new InvoiceQueryVo();
        queryVo.setNsrsbh("91330211MA2H46DFX5");
        queryVo.setFplxdm("026");
        queryVo.setCxfs("1");
        queryVo.setCxtj("1673675057427");

        StringWriter writer = new StringWriter();
        @Cleanup InputStream inputStream2 = new ClassPathResource("VatInvoiceQuery.xml").getInputStream();
        IOUtils.copy(inputStream2, writer, StandardCharsets.UTF_8.name());
        String s = StrUtil.format(writer.toString(), queryVo.toMap());


        HashMap<String, Object> map = Maps.newHashMap();
        map.put("appId", prodappid);
        map.put("appScrt", prodappScrt);
        String post = HttpUtil.post(prodtokenUrl, map, 2000);
        System.out.println("数族返回tokenJson" + post);


        JSONObject retJson = (JSONObject) JSONObject.parse(post);
        JSONObject result = retJson.getJSONObject("result");
        String token = result.getString("accessToken");
        LocalDateTime now = LocalDateTime.now();
        String sign = Base64.encodeBase64String(DigestUtils.md5Hex(s + now.toString() + token).getBytes());
        String body = HttpUtil.createPost(prodkpurl)
                .body(s)
                .header("AppId", prodappid)
                .header("SDate", now.toString())
                .header("Content-MD5", sign)
                .header("Content-Type", "application/xml")
                .execute().body();
        System.out.println(body);
        Object postResultSet = InvoiceUtils.getPostResultSet(body);
        System.out.println(postResultSet);

    }
}
