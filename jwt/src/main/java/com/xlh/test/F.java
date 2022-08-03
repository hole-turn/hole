package com.xlh.test;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: F
 * @projectName: hole
 * @description:
 * @date: 16:56 2022/8/3
 */
public class F {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("123123");

        TimeUnit.SECONDS.sleep(1);

        ThreadUtil.execute(()->{
            System.out.println("12314423");
        });

        System.out.println("wancheng");
    }
}
