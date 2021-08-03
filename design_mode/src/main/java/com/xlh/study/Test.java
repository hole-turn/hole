package com.xlh.study;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: xielinhao
 * @title: Test
 * @projectName: hole
 * @description:
 * @date: 10:10 2021/7/31
 */
public class Test {
    public static void main(String[] args) {

//        Date date1=new Date();
//        System.out.println(date1);
//       String dateStr1 = "2021-08-02
//       22:33:23";
//        Date date2 = DateUtil.parse(dateStr1);
//
//        System.out.println(date2);
//
//
//        long between = DateUtil.between(date1, date2, DateUnit.DAY);
//        System.out.println(between);
        BigDecimal bigDecimal2 = new BigDecimal("0");
        BigDecimal bigDecimal = new BigDecimal("0.12");
        System.out.println(bigDecimal2.add(bigDecimal));
    }
}
