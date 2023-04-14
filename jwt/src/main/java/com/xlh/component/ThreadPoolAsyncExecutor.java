package com.xlh.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @auther: XieLinhao
 * @date: 2023/4/14 11:06
 * @description:
 */
@Component
@Slf4j
public class ThreadPoolAsyncExecutor {

    private ExecutorService executorService;

    /**
     * 常见的有界队列
     * ArrayBlockingQueue ：基于数组实现的阻塞队列
     * LinkedBlockingQueue ：基于链表实现的阻塞队列，该有界队列不设置大小时就是Integer.MAX_VALUE
     * SynchronousQueue ：内部容量为零，适用于元素数量少的场景，尤其特别适合做交换数据用，内部使用队列来实现公平性的调度，使用栈来实现非公平的调度，在Java6时使用CAS代替了原来的锁逻辑
     */
    public synchronized ExecutorService getAsyncExecutor() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(
                    10,//核心线程数
                    20,//最大线程数
                    20,
                    TimeUnit.SECONDS,
                    new SynchronousQueue<>(),
//                    new LinkedBlockingQueue<>(),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy()
            );
            return executorService;
        } else {
            return executorService;
        }
    }

    public void execute(Runnable command) {
        getAsyncExecutor().execute(command);
    }

}
