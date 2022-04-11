package com.xlh.thread.lock;

/**
 * @author: xielinhao
 * @title: ReEntryLockDemo
 * @projectName: holeturn
 * @description:
 * @date: 14:47 2022/4/11
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
 * 简单的来说就是：在一个synchronized修饰的方法或代码块的内部调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 */
public class ReEntryLockDemo {
    static final Object objectLock = new Object();

    static Lock lock =new ReentrantLock();

    public static void main(String[] args) {

        new Thread(() ->{
            try{
                lock.lock();
                System.out.println("外层");
                try{
                    lock.lock();
                    System.out.println("内层");
                }finally {
                    // 这里故意注释，实现加锁次数和释放次数不一样
                    // 由于加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直在等待。
                    // 正常情况，加锁几次就要解锁几次
//                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"a").start();

        new Thread(() ->{
            try{
                lock.lock();
                System.out.println("b外");
            }finally {
                lock.unlock();
            }
        },"b").start();

    }

    private static void reEntryLock1() {
        new Thread(() -> {
            synchronized (objectLock){
                System.out.println("外层");
                synchronized (objectLock) {
                    System.out.println("中层");
                    synchronized (objectLock) {
                        System.out.println("内层");
                    }
                }
            }
        }, "a").start();
    }

    private static void reEntryLock2() {
        new ReEntryLockDemo().m1();
    }

    public synchronized void m1(){
        System.out.println("m1");
        m2();
    }
    public synchronized void m2(){
        System.out.println("m2");
        m3();
    }
    public synchronized void m3(){
        System.out.println("m3");
    }


}
