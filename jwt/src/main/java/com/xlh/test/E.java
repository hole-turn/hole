package com.xlh.test;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Maps;
import com.sun.jndi.toolkit.url.Uri;
import com.xlh.test.model.Stocks;
import com.xlh.util.CollectorsUtil;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: xielinhao
 * @title: E
 * @projectName: holeturn
 * @description:
 * @date: 16:04 2022/4/25
 */
public class E {
    @SneakyThrows
    public static void main(String[] args) {


//        byte[] bytes = HttpUtil.downloadBytes("https://static.91medicine.net/images/20220422/8e9997ec-5f56-481e-9e97-7c6aea34c247.pdf");
//
//        InputStream inputStream = new ByteArrayInputStream(bytes);
//        DateTime parse = DateUtil.parse("2022-04-29 23:50:00", "yyyy-MM-dd HH:mm:ss");
//        Date date = parse.toJdkDate();
//        System.out.println(date.before(new Date()));
//        System.out.println(date);
//        System.out.println(RandomUtil.randomNumbers(32));
//        BigDecimal b1 = new BigDecimal("1");
//        BigDecimal b2 = new BigDecimal("3");
//        System.out.println(b1.subtract(b2).abs());
//        int i = b1.subtract(b2).compareTo(BigDecimal.ZERO);
//        System.out.println(i);
//        BigDecimal divide = BigDecimal.valueOf(8201).divide(new BigDecimal(100));
//        System.out.println(divide);
//
//        System.out.println(IdcardUtil.getAgeByIdCard("110101201508076291"));
//        List<Integer> list = Arrays.asList(1, 2);
//        System.out.println(list.stream().filter(s -> s > 1).collect(Collectors.toList()));
//        System.out.println(new BigDecimal("1.00").compareTo(BigDecimal.ZERO) > 0);


//        List<Stocks> stocks = Arrays.asList(
//                new Stocks("202201", "2022", new BigDecimal("1")),
//                new Stocks("202202", "2022", new BigDecimal("2")),
//                new Stocks("202301", "2023", new BigDecimal("1"))
//        );
//        Map<String, BigDecimal> collect = stocks.stream().collect(Collectors.groupingBy(Stocks::getBatchNum, CollectorsUtil.summingBigDecimal(Stocks::getOverallPackNum)));
//        for (Map.Entry<String, BigDecimal> entry : collect.entrySet()) {
//            System.out.println(entry.getKey()+entry.getValue());
//        }


        System.out.println(IdcardUtil.getAgeByIdCard("110101201508076291"));

    }

}
