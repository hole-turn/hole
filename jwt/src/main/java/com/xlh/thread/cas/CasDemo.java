package com.xlh.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xielinhao
 * @title: CasDemo
 * @projectName: holeturn
 * @description:
 * @date: 14:49 2022/6/8
 */
public class CasDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(100);

        System.out.println(atomicInteger.compareAndSet(100, 101) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(100, 102) + "\t" + atomicInteger.get());

    }
}
