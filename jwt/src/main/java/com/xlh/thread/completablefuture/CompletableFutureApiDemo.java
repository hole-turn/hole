package com.xlh.thread.completablefuture;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @author: xielinhao
 * @title: CompletableFutureApiDemo
 * @projectName: holeturn
 * @description:
 * @date: 14:49 2022/3/25
 */
public class CompletableFutureApiDemo {

    @SneakyThrows
    public static void main(String[] args) {


    }

    private static void m6() {
        System.out.println(CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenCompose(i -> {

            return CompletableFuture.supplyAsync(() -> i*3);
        }).join());
    }

    private static void m5() {
        System.out.println(CompletableFuture.supplyAsync(() -> 10)
                .thenCombine(CompletableFuture.supplyAsync(() -> 3), Integer::sum)
                .thenApply(a -> a*2)
                .thenCombine(CompletableFuture.supplyAsync(() -> 30),(r1, r2) -> r1*r2).join());
    }

    private static void m4() throws InterruptedException {
        System.out.println(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task1");
            return 10;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task2");
            return 20;
        }), r -> r).join());

        TimeUnit.SECONDS.sleep(5);
    }

    private static void m3() {
        CompletableFuture
                .supplyAsync(() -> 2)
                .thenApply(f -> f+2)
                .thenApply(f ->f*4)
                .thenAccept(System.out::println);
    }

    private static void two() {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(2);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    int i = 1 / 0;
                    return 1;
                })
                .thenApply(f -> f + 2)
                .thenApply(f -> f + 3)
                .thenApply(f -> f + 4)
                .whenComplete((c, e) -> {
                    if (e == null) {
                        System.out.println(c);
                    }
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
//        System.out.println(future.get());

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 1;
        }).handle((f, e) -> {
            System.out.println("第一步");
            int i=1/0;
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("第2步");
            return f + 3;
        }).handle((f, e) -> {
            System.out.println("第3步");
            return f + 4;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println(v);
            }
        }).exceptionally(e -> null);

//        System.out.println(future1.get());
//            TimeUnit.SECONDS.sleep(1);
    }

    private static void one() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });

//        System.out.println(future.get());
//        System.out.println(future.get(1,TimeUnit.SECONDS));

//
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(future.getNow(999));

        System.out.println(future.complete(999) + "\t" + future.get());
    }

}
