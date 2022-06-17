package com.xlh.thread.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: xielinhao
 * @title: LongAdderDemo2
 * @projectName: holeturn
 * @description:
 * @date: 16:10 2022/6/14
 */
public class LongAdderDemo2 {

    public static final Integer THREAD_NUM = 50;

    public static final Integer EXECUTIONS = 1000000;


    //50个线程，每个线程100W次，总点赞数出来
    public static void main(String[] args) {

        ClickNum clickNum = new ClickNum();
        long start;
        long end;

        CountDownLatch countDownLatch1 = new CountDownLatch(THREAD_NUM);
        CountDownLatch countDownLatch2 = new CountDownLatch(THREAD_NUM);
        CountDownLatch countDownLatch3 = new CountDownLatch(THREAD_NUM);
        CountDownLatch countDownLatch4 = new CountDownLatch(THREAD_NUM);
        CountDownLatch countDownLatch5 = new CountDownLatch(THREAD_NUM);

        //1
        start = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int k = 1; k <= EXECUTIONS; k++) {
                        clickNum.addSyncNumber();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("方法addSyncNumber()\t" + "耗时\t" + (end - start) + "ms");


        //2
        start = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int k = 1; k <= EXECUTIONS; k++) {
                        clickNum.addAtomicInteger();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("方法addAtomicInteger()\t" + "耗时\t" + (end - start) + "ms");

        //3
        start = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int k = 1; k <= EXECUTIONS; k++) {
                        clickNum.addAtomicLong();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch3.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("方法addAtomicLong()\t" + "耗时\t" + (end - start) + "ms");

        //4
        start = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int k = 1; k <= EXECUTIONS; k++) {
                        clickNum.addLongAdder();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch4.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("方法addLongAdder()\t" + "耗时\t" + (end - start) + "ms");

        //5
        start = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int k = 1; k <= EXECUTIONS; k++) {
                        clickNum.addLongAccumulator();
                    }
                } finally {
                    countDownLatch5.countDown();
                }
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch5.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("方法addLongAccumulator()\t" + "耗时\t" + (end - start) + "ms");

    }
}

class ClickNum {

    // synchronized
    int number = 0;

    public synchronized void addSyncNumber() {
        number++;
    }

    // atomicInteger
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomicInteger() {
        atomicInteger.incrementAndGet();
    }

    // atomicLong
    AtomicLong atomicLong = new AtomicLong();

    public void addAtomicLong() {
        atomicLong.incrementAndGet();
    }

    // longAdder
    LongAdder longAdder = new LongAdder();

    public void addLongAdder() {
        longAdder.increment();
    }

    // longAccumulator
    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public void addLongAccumulator() {
        longAccumulator.accumulate(1);
    }

}

