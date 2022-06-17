package com.xlh.thread.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xielinhao
 * @title: AtomicIntegerDemo
 * @projectName: holeturn
 * @description:
 * @date: 14:12 2022/6/13
 */
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        Number number = new Number();
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    for (int k = 0; k < 5000; k++) {
                        number.addPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }

            }, "a").start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(number.getInteger());
    }

}

class Number {
    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void addPlus() {
        atomicInteger.incrementAndGet();
    }

    public Integer getInteger() {
        return atomicInteger.get();
    }
}
