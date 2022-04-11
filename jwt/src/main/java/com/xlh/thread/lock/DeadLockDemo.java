package com.xlh.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: DeadLockDemo
 * @projectName: holeturn
 * @description:
 * @date: 16:58 2022/4/11
 */
public class DeadLockDemo {

    static final Object objectLock1 =new Object();
    static final Object objectLock2 =new Object();

    public static void main(String[] args) {
        new Thread(() ->{
            synchronized (objectLock1){
                System.out.println(Thread.currentThread().getName()+"\t 锁1 想要获得 锁2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectLock2){
                    System.out.println(Thread.currentThread().getName()+"\t 锁1 已经获得 锁2");
                }
            }
        },"a").start();

        new Thread(() ->{
            synchronized (objectLock2){
                System.out.println(Thread.currentThread().getName()+"\t 锁2 想要获得 锁1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectLock1){
                    System.out.println(Thread.currentThread().getName()+"\t 锁2 已经获得 锁1");
                }
            }
        },"b").start();
    }
}
