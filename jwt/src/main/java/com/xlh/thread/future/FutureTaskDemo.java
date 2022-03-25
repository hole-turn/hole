package com.xlh.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: FutureTaskDemo
 * @projectName: holeturn
 * @description:
 * @date: 14:23 2022/3/24
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws Exception {

        FutureTask<Integer> futureTask = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"come in");
            TimeUnit.SECONDS.sleep(2);
            return 250;
        });

        new Thread(futureTask,"a").start();

        System.out.println(futureTask.get());

        System.out.println("主线程继续执行");

//        while (true){
//            if (futureTask.isDone()){
//                System.out.println(futureTask.get());
//                break;
//            }else {
//                System.out.println("轮询中");
//            }
//        }

    }
}
