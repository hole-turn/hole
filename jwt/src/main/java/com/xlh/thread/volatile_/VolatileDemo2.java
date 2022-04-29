package com.xlh.thread.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: VolatileDemo2
 * @projectName: holeturn
 * @description:
 * @date: 13:49 2022/4/29
 */
public class VolatileDemo2 {
    public static void main(String[] args) {
        Calc calc=new Calc();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    calc.addPlus();
                }
            }).start();
        }
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(calc.num);
    }
}
class Calc{

    volatile int num;
    public void addPlus(){
        num++;
    }
}
