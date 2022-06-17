package com.xlh.thread.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: xielinhao
 * @title: LongAdderDemo
 * @projectName: holeturn
 * @description:
 * @date: 15:42 2022/6/14
 */
public class LongAdderDemo {

    public static void main(String[] args) {

        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
//        longAdder.decrement();
//        longAdder.reset();
        longAdder.increment();

        System.out.println(longAdder.longValue());


        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x - y, 20);
        longAccumulator.accumulate(10);
        longAccumulator.accumulate(20);
        longAccumulator.accumulate(30);
        System.out.println(longAccumulator.longValue());
    }
}
