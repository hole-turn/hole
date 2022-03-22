package com.xlh.test;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;
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
        String[] strings = {"https://dev.91medicine.net/upload/c/20220314/51e2ac5163d54c908d17a916334db69f.png", " https://dev.91medicine.net/upload/c/20220314/842cb73df55c437f90a7c743d69f6718.png"};
//     new String[]{"https://dev.91medicine.net/upload/c/20220314/51e2ac5163d54c908d17a916334db69f.png", "https://dev.91medicine.net/upload/c/20220314/842cb73df55c437f90a7c743d69f6718.png"};
        String s = ArrayUtil.toString(strings);
        System.out.println(s);
        System.out.println(Arrays.toString(strings));

        List<String> strings1 = Arrays.asList(strings);
        String join = CollectionUtil.join(strings1, ",");
        System.out.println(join.trim());

    }
}
