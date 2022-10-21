package com.xlh.test.model;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: G
 * @projectName: hole
 * @description:
 * @date: 10:51 2022/9/1
 */
public class G {
    public static final String STR="123:1";
    public static void main(String[] args) {
//        List<String> betweenDate = getBetweenDate("2022-09-28", "2021-10-06");
//        System.out.println(betweenDate);
//        LocalTime start = LocalTime.parse("14:22");
//
//        LocalTime end = LocalTime.parse("14:24");
//
//        LocalTime now = LocalTime.now();
//
//        System.out.println(now.compareTo(end));
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add("123");
        list.add("");
        List<String> strings = CollectionUtil.removeBlank(list);
        System.out.println(strings);
        System.out.println(list);

    }

    /**
     *  获取两个日期之间的所有日期 (年月日)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
