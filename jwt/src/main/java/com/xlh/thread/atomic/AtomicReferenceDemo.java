package com.xlh.thread.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author: xielinhao
 * @title: AtomicReferenceDemo
 * @projectName: holeturn
 * @description:
 * @date: 14:23 2022/6/13
 */
public class AtomicReferenceDemo {

    static AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference<>(100, false);

    public static void main(String[] args) {

        new Thread(()->{
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t"+"1次标记号"+marked);

            atomicMarkableReference.compareAndSet(100,101,marked,!marked);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"\t"+"2次标记号"+atomicMarkableReference.isMarked());

            atomicMarkableReference.compareAndSet(101,100,marked,!marked);

            System.out.println(Thread.currentThread().getName()+"\t"+"3次标记号"+atomicMarkableReference.isMarked());

        },"a").start();

        new Thread(()->{
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t"+"1次标记号"+marked);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicMarkableReference.compareAndSet(100,2000,marked,!marked);
            System.out.println(Thread.currentThread().getName()+"\t"+"2次标记号"+atomicMarkableReference.isMarked());

            System.out.println(Thread.currentThread().getName()+"\t"+atomicMarkableReference.getReference()+"\t"+atomicMarkableReference.isMarked());

        },"b").start();
    }
}
