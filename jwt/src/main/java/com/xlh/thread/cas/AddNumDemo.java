package com.xlh.thread.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: xielinhao
 * @title: AddNumDemo
 * @projectName: holeturn
 * @description:
 * @date: 15:56 2022/6/7
 */
public class AddNumDemo {

    public static void main(String[] args) {
//        m1();
        Number number=new Number();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int k = 0; k < 1000; k++) {
                    number.addAtomic();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(number.getAtomic());
    }

    private static void m1() {
        Number number=new Number();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int k = 0; k < 1000; k++) {
                    number.add();
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(number.num);
    }
}


class Number{
    volatile int num = 0;
    public synchronized void add(){
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
    public int getAtomic(){
       return atomicInteger.get();
    }
}
