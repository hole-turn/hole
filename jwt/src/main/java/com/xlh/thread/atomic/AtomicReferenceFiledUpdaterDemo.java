package com.xlh.thread.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author: xielinhao
 * @title: AtomicReferenceFiledUpdaterDemo
 * @projectName: holeturn
 * @description:
 * @date: 15:20 2022/6/14
 */
public class AtomicReferenceFiledUpdaterDemo {
    public static void main(String[] args) {

        MyBro myBro = new MyBro();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                myBro.init(myBro);
            }, String.valueOf(i)).start();
        }
    }
}

class MyBro {
    public volatile Boolean isInit = Boolean.FALSE;

    AtomicReferenceFieldUpdater<MyBro, Boolean> atomicReferenceFieldUpdater
            = AtomicReferenceFieldUpdater.newUpdater(MyBro.class, Boolean.class, "isInit");

    public void init(MyBro myBro) {

        if (atomicReferenceFieldUpdater.compareAndSet(myBro, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + "\t" + "init...");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "---init.....over");
        } else {
            System.out.println(Thread.currentThread().getName() + "\t" + "------其它线程正在初始化");
        }

    }
}
