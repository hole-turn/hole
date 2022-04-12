package com.xlh.thread.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: xielinhao
 * @title: InterruptDemo
 * @projectName: holeturn
 * @description:
 * @date: 10:25 2022/4/12
 */
public class InterruptDemo {


    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("isInterrupted 为 true 线程中断");
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    //没有它，程序不会停止，中断不打断 sleep源码
                    /**
                     *  * <p> If this thread is blocked in an invocation of the {@link
                     *      * Object#wait() wait()}, {@link Object#wait(long) wait(long)}, or {@link
                     *      * Object#wait(long, int) wait(long, int)} methods of the {@link Object}
                     *      * class, or of the {@link #join()}, {@link #join(long)}, {@link
                     *      * #join(long, int)}, {@link #sleep(long)}, or {@link #sleep(long, int)},
                     *      * methods of this class, then its interrupt status will be cleared and it
                     *      * will receive an {@link InterruptedException}.
                     */
//                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("runing");
            }
        }, "a");

        thread1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> thread1.interrupt(), "b").start();

    }

    private static void m4() {
        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 300; i++) {
                System.out.println("num----" + i);
            }
            System.out.println("after t1.interrupt()--第2次---:" + Thread.currentThread().isInterrupted());

        }, "a");

        thread1.start();

        System.out.println("before t1.interrupt()---:" + thread1.isInterrupted());

        //活动状态,t1线程还在执行中
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //实例方法interrupt()仅仅是设置线程的中断状态位设置为true，不会停止线程
        thread1.interrupt();
        System.out.println("after t1.interrupt()--第1次---:" + thread1.isInterrupted());

        //非活动状态,t1线程不在执行中，已经结束执行了。
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after t1.interrupt()--第3次---:" + thread1.isInterrupted());
    }

    private static void m3() {
        Thread thread1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("isInterrupted 为 true 线程中断");
                    break;
                }
                System.out.println("runing");
            }
        }, "a");

        thread1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> thread1.interrupt(), "b").start();
    }


    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private static void m2() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println("atomicBoolean 为 true 线程中断");
                    break;
                }
                System.out.println("runing");
            }
        }, "a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> atomicBoolean.set(true), "b").start();
    }

    static volatile boolean flag = false;

    private static void m1() {
        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println("flag 为 true 线程中断");
                    break;
                }
                System.out.println("runing");
            }
        }, "a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> flag = true, "b").start();
    }

}
