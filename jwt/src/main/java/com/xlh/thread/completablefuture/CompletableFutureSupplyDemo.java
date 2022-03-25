package com.xlh.thread.completablefuture;

import java.util.concurrent.*;

/**
 * @author: xielinhao
 * @title: CompletableFutureSupplyDemo
 * @projectName: holeturn
 * @description:
 * @date: 16:07 2022/3/24
 */
public class CompletableFutureSupplyDemo {
    public static void main(String[] args)throws Exception {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in ");
            return 10;
        });

        System.out.println(completableFuture1.get());

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in ");
            return 20;
        },executor);

        System.out.println(completableFuture2.get());

        executor.shutdown();
    }
}
