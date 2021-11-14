package com.xlh.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author: xielinhao
 * @title: DateRandom
 * @description:
 * @date: 12:58 2021/11/14
 */
public class DateRandom {

    ////随机生成区间月份数据没有hh:mm:ss
    public static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    public static String numRandom() {
        StringBuilder sb = new StringBuilder();
        //随机生成时
        int hh = RandomUtil.randomInt(0, 24);
        String _hh = String.format("%02d", hh);
        //随机生成分
        int mm = RandomUtil.randomInt(0, 60);
        String _mm = String.format("%02d", mm);
        //随机生成秒
        int ss = RandomUtil.randomInt(0, 60);
        String _ss = String.format("%02d", ss);
        sb.append(_hh).append(":").append(_mm).append(":").append(_ss);
        return sb.toString();
    }

    ////随机生成区间月份数据有hh:mm:ss
    public static Date getDateRandom(String beginDate, String endDate) {
        String format = DateUtil.format(randomDate(beginDate, endDate), "yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        String s = sb.append(format).append(" ").append(numRandom()).toString();
        System.out.println(s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date parse = null;
        try {
            parse = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static int getTimeNumRandom() {
        return RandomUtil.randomInt(90, 150);
    }


    public static void main(String[] args) {


    }

    //将分转化为HH:mm:ss
    public static String minuteToHHMMSS(int minute) {
        String result = null;
        try {
            Date date = new Date((minute % 86400 * 60) * 1000);

            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT+0.00"));
            result = df.format(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
