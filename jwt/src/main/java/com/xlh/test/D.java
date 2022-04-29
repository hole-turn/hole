package com.xlh.test;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.google.common.collect.Maps;
import com.xlh.entity.User;
import com.xlh.expection.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: xielinhao
 * @title: D
 * @projectName: hole
 * @description:
 * @date: 10:14 2022/3/9
 */
public class D {

    public static void main(String[] args) {

//        AtomicInteger integer= new AtomicInteger(1);
//        System.out.println( integer.incrementAndGet());
//
//        int i = integer.incrementAndGet();
//        System.out.println(i);

//        List<String> strings = Arrays.asList("a", "b", "c");
//        String join = CollectionUtil.join(strings, ",");
//        System.out.println(join);
//
//        DateTime birthDate = IdcardUtil.getBirthDate("330227199903050016");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(birthDate);
//        System.out.println(format);
//        String[] strings = {"https://dev.91medicine.net/upload/c/20220314/51e2ac5163d54c908d17a916334db69f.png", " https://dev.91medicine.net/upload/c/20220314/842cb73df55c437f90a7c743d69f6718.png"};
////     new String[]{"https://dev.91medicine.net/upload/c/20220314/51e2ac5163d54c908d17a916334db69f.png", "https://dev.91medicine.net/upload/c/20220314/842cb73df55c437f90a7c743d69f6718.png"};
//        String s = ArrayUtil.toString(strings);
//        System.out.println(s);
//        System.out.println(Arrays.toString(strings));
//
//        List<String> strings1 = Arrays.asList(strings);
//        String join = CollectionUtil.join(strings1, ",");
//        System.out.println(join.trim());


//        boolean b = "无" != null && (!"".equals("无") && !"无".equals("无"));
//        System.out.println(b);
//        BigDecimal a=new BigDecimal("1.99");
//        BigDecimal b=new BigDecimal("2");
//        String s1 = a.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
//        String s2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

//        System.out.println(!(s1.compareTo(s2)> -1));

//        HashMap<Integer, String> map = Maps.newHashMap();
//        map.put(1,"a");
//
//        System.out.println(map.get(2));
//        System.out.println("3".equals(null));
//        HashMap<String, String> map = Maps.newHashMap();
//        System.out.println("a".equalsIgnoreCase(map.get("a")));

        BigDecimal bigDecimal = new BigDecimal("100");

        BigDecimal bigDecimal2 = new BigDecimal("101");

        System.out.println(bigDecimal.compareTo(bigDecimal2));
//        if (bigDecimal.compareTo(BigDecimal.ZERO) < 0) {
//            if (bigDecimal.compareTo(BigDecimal.ZERO) <= 0) {
//                System.out.println("111");
//            }
//        }


    }

}
