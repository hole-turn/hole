package com.xlh.test;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: xielinhao
 * @title: F
 * @projectName: hole
 * @description:
 * @date: 16:56 2022/8/3
 */
public class F {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("123123");
//
//        TimeUnit.SECONDS.sleep(1);
//
//        ThreadUtil.execute(()->{
//            System.out.println("12314423");
//        });
//
//        System.out.println("wancheng");

//        Map map = JSONObject.parseObject(param, Map.class);
//        System.out.println(map.get("sysRetCode"));
//        System.out.println(map);
        JSONObject json = (JSONObject) JSONObject.parse(param);
//        String sysRetCode = json.getString("sysRetcode");
//        Map<String, Object> data = json.getJSONObject("data").getInnerMap();
        Map<String, Object> innerMap = json.getInnerMap();
        innerMap.entrySet() .stream().forEach(System.out::println);}


    public static final String param ="{\"sign\":\"cYP7RpnOu/3A5i9uqHgJ4kX5jn6aztspU4PFSY6I4jcp1xEZ8ZAYz/UvG/PrwOt0VRM+NdpOYoXtL4ClwwRq+l2FL1rsjAf0opcMvjJPirJxsjCAEXVcnVcSlMw1OoPfEW52x56dn2g1y1dcSBe/wHR3ZQKaJEzZdX3hFLTNJCU=\",\"data\":{\"finRetcode\":\"00\",\"appendRetcode\":\"TRADE_SUCCESS\",\"appendRetmsg\":\"交易支付成功\",\"tradeCode\":\"WAC2B\",\"pcTrace\":\"2022081022001420091427361050\",\"outTrace\":\"2111557204352157044736\",\"payerId\":\"2088702224720095\",\"payerOtherInfo\":\"511***@qq.com\",\"timeEnd\":\"20220810111642\",\"settleAmt\":1,\"payerAmt\":1,\"patnerSettleFlag\":\"1\",\"oriOrgTrace\":\"ORG0478520220810111615G7UUM6\"},\"sysRetcode\":\"000000\",\"sysRetmsg\":\"success\",\"appendData\":{\"payDetail\":{\"aliFundBillList\":[{\"fund_channel\":\"ALIPAYACCOUNT\",\"amount\":\"0.01\"}]},\"disCount\":{},\"riskInfo\":{}}}";
}
