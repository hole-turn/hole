package com.xlh.thread.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xielinhao
 * @title: SyncDemo
 * @projectName: holeturn
 * @description:
 * @date: 15:05 2022/4/7
 */
public class SyncDemo {

    private Object o = new Object();

    public void m1() {
        synchronized (o) {
            System.out.println("hello world");
        }
    }
    public void m2() {
        synchronized (o) {
            System.out.println("hello world");
            throw new RuntimeException("ex");
        }
    }

    public synchronized void m3(){
        System.out.println("hello world");
    }

    public static synchronized void m4(){
        System.out.println("hello world");
    }

}
