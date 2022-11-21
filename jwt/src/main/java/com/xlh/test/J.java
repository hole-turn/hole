package com.xlh.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdcardUtil;

/**
 * @author: xielinhao
 * @title: J
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 9:49 2022/11/11
 */
public class J {
    public static void main(String[] args) {
        DateTime birthDate = IdcardUtil.getBirthDate("42272519531011008");
        String s = birthDate.toString(DatePattern.NORM_DATE_PATTERN);
        System.out.println(s);
    }
}
