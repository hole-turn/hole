package com.xlh.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: xielinhao
 * @title: Times
 * @projectName: hole
 * @description:
 * @date: 14:31 2021/11/2
 */
public class Times {
    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
//        String s = DateUtil.format(new Date(), simpleDateFormat);
//        System.out.println(s);
//        LocalDateTime offset = LocalDateTimeUtil.offset(now, 180, ChronoUnit.DAYS);
//        System.out.println(offset);
//
//        long l = LocalDateTimeUtil.between(now, offset).toDays();
//        System.out.println(l);

//        String substring = "330212201602292316".substring(6, 14);
//        DateTime age = DateUtil.parse(substring, "yyyyMMdd");
//        int a = DateUtil.ageOfNow(age);
//        System.out.println(a);
//        System.out.println(a<=6);
        Calendar calendar = DateUtil.calendar(new Date());
        calendar.add(Calendar.DATE, -2);
        Date time = calendar.getTime();
        System.out.println(time);

    }
}
