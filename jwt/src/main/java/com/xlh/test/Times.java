package com.xlh.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDate;

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
//        Calendar calendar = DateUtil.calendar(new Date());
//        calendar.add(Calendar.DATE, -2);
//        Date time = calendar.getTime();
//        System.out.println(time);
//        String str="01234567899876543210aaa";
//        String substring = str.substring(0, 20);
//        System.out.println(substring);
//
//        String format = String.format("%04d", 100);
//        System.out.println("JG"+format);
//        DateTime age = DateUtil.parse("330225199501312308".substring(6, 14), "yyyyMMdd");
//        System.out.println(IdcardUtil.getBirthDate("330225199501312308"));

//        List<String> strings = Arrays.asList("中药", "药");
//        StringBuilder sb=new StringBuilder();
//        StringBuilder sb2=new StringBuilder();
//        if (strings.contains("中药")) {
//            sb.append("中成药").append(",");
//            sb2.append("01").append(",");
//        }
//        if (strings.contains("化药")){
//            sb.append("西药").append(",");
//            sb2.append("02").append(",");
//        }
//        String s = sb.toString();
//        String s1 = sb2.toString();
//        System.out.println(s.substring(0, s.length() - 1));
//        System.out.println(s1.substring(0, s1.length() - 1));
//        String yyyyMMdd = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMdd");
//        System.out.println(yyyyMMdd);
        LocalDate localDate = LocalDateTimeUtil.parseDate("2022-03-01", DatePattern.NORM_DATE_PATTERN);
//        LocalDate localDate2 = LocalDateTimeUtil.parseDate("2022-03-02", "yyyy-MM-dd");
//        System.out.println(localDate);
//        System.out.println(localDate.isBefore(LocalDate.now()));

        int i = localDate.compareTo(LocalDate.now());//当天为0 当天后+  当天前-
        System.out.println(i);
        System.out.println(localDate.compareTo(LocalDate.now())<0);

//        LocalDateTime parse = LocalDateTimeUtil.parse("2022-03-01 12:50:01", "yyyy-MM-dd HH:mm:ss");
//        System.out.println(parse);
//        System.out.println(parse.isBefore(LocalDateTime.now()));

//        String a="abcd";
//        System.out.println(a.substring(0, a.length() - 1));
    }
}
