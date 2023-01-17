package com.xlh.invoic;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xlh.invoic.config.InvoiceConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xielinhao
 * @title: InvoiceUtils
 * @projectName: cloudShop
 * @description:
 * @date: 16:35 2022/12/14
 */
public class InvoiceUtils {

    private static final Logger log = LoggerFactory.getLogger(InvoiceUtils.class);

    public static String getAccessToken(InvoiceConfig config) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("appId", config.getAppId());
        map.put("appScrt", config.getAppScrt());
        String post = HttpUtil.post(config.getTokenUrl(), map, 2);
        log.info("数族返回tokenJson,{}", post);

        if (StringUtils.isBlank(post)) {
            return null;
        }

        JSONObject retJson = (JSONObject) JSONObject.parse(post);
        if (codeIsOk(retJson.getString("code"))) {
            JSONObject result = retJson.getJSONObject("result");
            return result.getString("accessToken");
        } else {
            return null;
        }
    }

    public static boolean codeIsOk(String code) {
        if (StringUtils.isNotBlank(code) && "0".equals(code)) {
            return true;
        }
        return false;
    }

    public static Object getPostResultSet(String post) {
        Map<String, Object> map = XmlUtil.xmlToMap(post);
        return map.get("body");
    }
}
