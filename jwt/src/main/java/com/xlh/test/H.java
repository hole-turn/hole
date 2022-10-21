package com.xlh.test;


import cn.hutool.core.collection.CollectionUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: xielinhao
 * @title: H
 * @projectName: hole
 * @description:
 * @date: 14:21 2022/10/8
 */
public class H {
    public static void main(String[] args){
        String s = "红霉素肠溶片 硝苯地平缓释片";

        List<String> strings = Arrays.asList(s.split(" "));
        System.out.println(strings);
    }
}
