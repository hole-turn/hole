package com.xlh.thread.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: xielinhao
 * @title: SpinLockDemo
 * @projectName: holeturn
 * @description:
 * @date: 15:31 2022/6/8
 */
//自旋锁实现
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference =new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"\t"+"come in");
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {

        }
        System.out.println(thread.getName()+"\t"+"持有锁成功");
    }
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        boolean b = atomicReference.compareAndSet(Thread.currentThread(), null);
        System.out.println(thread.getName()+"\t"+"释放锁成功");
    }

    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{

            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();

        },"a").start();


        new Thread(()->{
            //暂停一会儿线程，保证A线程先于B线程启动并完成
            try { TimeUnit.SECONDS.sleep( 1 ); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();

        },"b").start();

    }
}
