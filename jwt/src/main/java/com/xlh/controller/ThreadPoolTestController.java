package com.xlh.controller;

import com.xlh.common.base.Result;
import com.xlh.component.ThreadPoolAsyncExecutor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @auther: XieLinhao
 * @date: 2023/4/14 11:19
 * @description:
 */
@RestController
@RequestMapping("thread/pool")
@Api(tags = "线程池测试")
public class ThreadPoolTestController {

    @Autowired
    private ThreadPoolAsyncExecutor executor;

    /**
     *
     * 根据{@link ThreadPoolAsyncExecutor#getAsyncExecutor()} 创建的线程池（核心10 最大20）
     * 首先调用 {@link ThreadPoolTestController#start1()}  会占用10个线程（使用核心）
     * 再调用 {@link ThreadPoolTestController#start2()}  其次进入工作队列 因为用的是SynchronousQueue，没有容量，所以会再开启10个线程，达到最大20
     * 再调用 {@link ThreadPoolTestController#start3()}  因为用的是AbortPolicy 超过20个线程了 所以直接报错
     *
     */

    @ApiOperation("开启多线程1")
    @GetMapping("test1")
    public Result start1() {
        //因为核心线程数是10 所以这边设置10个
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> method("第一个方法"));
        }
        return Result.success();
    }

    @ApiOperation("开启多线程2")
    @GetMapping("test2")
    public Result start2() {
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> method("第二个方法"));
        }
        return Result.success();
    }

    @ApiOperation("开启多线程3")
    @GetMapping("test3")
    public Result start3() {
        executor.execute(() -> method("第三个方法"));
        return Result.success();
    }

//=================================================分 割 线====================================================================

    @ApiOperation("CompletableFuture开启多线程1")
    @GetMapping("completable/test1")
    public Result completableStart1() {
        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(() -> method("第一个方法"), executor.getAsyncExecutor());
        }
        return Result.success();
    }

    @ApiOperation("CompletableFuture开启多线程2")
    @GetMapping("completable/test2")
    public Result completableStart2() {
        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(() -> method("第二个方法"), executor.getAsyncExecutor());
        }
        return Result.success();
    }

    @ApiOperation("CompletableFuture开启多线程3")
    @GetMapping("completable/test3")
    public Result completableStart3() {
        CompletableFuture.runAsync(() -> method("第三个方法"), executor.getAsyncExecutor());
        return Result.success();
    }


    private void method(String method) {
        System.out.println(method + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(method + Thread.currentThread().getName() + "……执行完成");
    }
}
