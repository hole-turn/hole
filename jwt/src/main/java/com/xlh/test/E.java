package com.xlh.test;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.sun.jndi.toolkit.url.Uri;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        System.out.println(IdcardUtil.getAgeByIdCard("110101201508076291"));

    }
}
