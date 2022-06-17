package com.xlh.thread.cas;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: xielinhao
 * @title: ABADemo
 * @projectName: holeturn
 * @description:
 * @date: 15:34 2022/6/10
 */
public class ABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);

    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {

        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        }, "a").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(100, 2021) + "\t" + atomicInteger.get());
        }, "b").start();
        //暂停一会儿线程,main彻底等待上面的ABA出现演示完成。
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("============以下是ABA问题的解决=============================");


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "版本号" + stamp);

            try { Thread.sleep( 1000 ); } catch (InterruptedException e) { e.printStackTrace(); }

            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + "\t" + "二次版本号" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + "\t" + "三次版本号" + atomicStampedReference.getStamp());

        }, "t1").start();

        new Thread(() ->{

            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t" + "首次版本号" + stamp);

            //暂停一会儿线程，获得初始值100和初始版本号1，故意暂停3秒钟让t1线程完成一次ABA操作产生问题
            try { Thread.sleep( 3000 ); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean result = atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t"+result+"\t"+atomicStampedReference.getReference());


        },"t2").start();

    }
}
