package com.xlh.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: CompletableFutureDemo
 * @projectName: holeturn
 * @description:
 * @date: 16:45 2022/3/24
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception{

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        })
                .thenApply(f -> f + 2)
                .whenComplete((v, s) -> {
                    if (s == null) {
                        System.out.println("result==>" + v);
                    }
                }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });

//        CompletableFuture<Integer> integerCompletableFuture =
//                CompletableFuture.supplyAsync(() -> 3)
//                        .thenApplyAsync(a -> a + 2)
//                        .thenApplyAsync(a -> a + 4);
//        System.out.println(integerCompletableFuture.get());

        System.out.println("main");

        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:暂停3秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
