package com.xlh.thread.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: VolatileDemo
 * @projectName: holeturn
 * @description:
 * @date: 13:42 2022/4/29
 */
public class VolatileDemo {

   volatile static boolean flag = true;

    public static void main(String[] args) {

        new Thread(()->{

            System.out.println(Thread.currentThread().getName()+"\t"+"come in");
            while (flag){

            }
            System.out.println(Thread.currentThread().getName()+"\t"+"end");
        },"a").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag=false;
    }
}
