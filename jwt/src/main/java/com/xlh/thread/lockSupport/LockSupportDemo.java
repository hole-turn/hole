package com.xlh.thread.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: xielinhao
 * @title: LockSupportDemo
 * @projectName: holeturn
 * @description:
 * @date: 10:39 2022/4/18
 */
public class LockSupportDemo {


    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("进入"+Thread.currentThread().getName()+"线程");
            LockSupport.park();
            LockSupport.park();
            System.out.println("线程"+Thread.currentThread().getName()+"被唤醒");
        }, "a");

        t1.start();

        new Thread(() ->{
            LockSupport.unpark(t1);
            System.out.println("线程"+Thread.currentThread().getName()+"发起通知");
        },"b").start();

        new Thread(() ->{
            LockSupport.unpark(t1);
            System.out.println("线程"+Thread.currentThread().getName()+"发起通知");
        },"c").start();
    }

    private static void m3() {
        Thread t1 = new Thread(() -> {
            System.out.println("进入"+Thread.currentThread().getName()+"线程");
            LockSupport.park();
            System.out.println("线程"+Thread.currentThread().getName()+"被唤醒");
        }, "a");

        t1.start();


        new Thread(() ->{

            LockSupport.unpark(t1);
            System.out.println("线程"+Thread.currentThread().getName()+"发起通知");

        },"b").start();
    }


    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    private static void m2() {
        new Thread(() ->{
            lock.lock();
            try{
                System.out.println("a线程进来");

                condition.await();

                System.out.println("a线程被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        },"a").start();

        new Thread(() ->{
            lock.lock();
            try{
                condition.signal();
                System.out.println("b线程发出通知");
            }finally {
                lock.unlock();
            }

        },"b").start();
    }


    static final Object lockSupport = new Object();

    private static void m1() {
        //Object类中的wait、notify、notifyAll 用于线程等待和唤醒的方法，都必须在synchronized内部执行（必须使用synchronized关键字）
        new Thread(() -> {
            synchronized (lockSupport) {
                System.out.println("进入a线程等待");
                try {
                    lockSupport.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("收到b线程通知,等待完成");
            }
        }, "a").start();

        new Thread(() -> {
            synchronized (lockSupport) {
                lockSupport.notify();
                System.out.println("通知a线程释放");
            }
        }, "b").start();
    }
}
