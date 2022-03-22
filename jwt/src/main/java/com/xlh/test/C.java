package com.xlh.test;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author: xielinhao
 * @title: C
 * @projectName: hole
 * @description:
 * @date: 12:55 2022/1/20
 */
public class C {
    public static void main(String[] args) {
//        String s = "YP" + String.format("%08d", 12544 + 1);
//        System.out.println(s);
//        TimeInterval timer = DateUtil.timer();
//        ThreadUtil.sleep(1000);
//        long l = timer.intervalMs();
//        long interval = timer.interval();
//        System.out.println(interval);
//        long l1 = timer.intervalMinute();
//        System.out.println(l1);
//        System.out.println(l);
//        System.out.println(313%60);

        Map<Integer, String> map = Maps.newHashMap();
        map.put(1,"one");
        int i = map.get(1).hashCode();
        System.out.println(i);
    }
}
