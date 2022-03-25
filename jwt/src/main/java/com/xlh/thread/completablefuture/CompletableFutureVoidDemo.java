package com.xlh.thread.completablefuture;

import java.util.concurrent.*;

/**
 * @author: xielinhao
 * @title: CompletableFutureDemo
 * @projectName: holeturn
 * @description:
 * @date: 15:27 2022/3/24
 */
public class CompletableFutureVoidDemo {
    public static void main(String[] args) throws Exception{

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(completableFuture1.get());
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "come in ");
        }, executor);

        System.out.println(completableFuture2.get());

        executor.shutdown();

    }
}
