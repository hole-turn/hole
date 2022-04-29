package com.xlh.thread.volatile_.singleton;

/**
 * @author: xielinhao
 * @title: SafeDoubleCheckSingleton
 * @projectName: holeturn
 * @description:
 * @date: 15:16 2022/4/29
 */
public class SafeDoubleCheckSingleton {
    //私有化构造方法
    private SafeDoubleCheckSingleton(){

    }

    private volatile static SafeDoubleCheckSingleton singleton = null;

    // 双重锁设计
    public static SafeDoubleCheckSingleton getInstance(){
        if (singleton == null){
            //1.多线程并发创建对象时，会通过加锁保证只有一个线程能创建对象
            synchronized (SafeDoubleCheckSingleton.class){
                if (singleton == null){
                    //隐患：多线程环境下，由于重排序，该对象可能还未完成初始化就被其他线程读取
                    singleton=new SafeDoubleCheckSingleton();
                }
            }
        }
        //2.对象创建完毕，执行getInstance()将不需要获取锁，直接返回创建对象
        return singleton;
    }
}
