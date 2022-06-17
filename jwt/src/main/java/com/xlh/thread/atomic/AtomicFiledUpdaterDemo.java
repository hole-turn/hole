package com.xlh.thread.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: xielinhao
 * @title: AtomicFiledUpdaterDemop
 * @projectName: holeturn
 * @description:
 * @date: 15:37 2022/6/13
 */
public class AtomicFiledUpdaterDemo {

    /**
     * 以一种线程安全的方式操作非线程安全对象的某些字段。
     * 需求：
     * 1000个人同时向一个账号转账一元钱，那么累计应该增加1000元，
     * 除了synchronized和CAS,还可以使用AtomicIntegerFieldUpdater来实现。
     */
    public static void main(String[] args) {
        Bank bank = new Bank();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                bank.transferBank(bank);
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(bank.money);
    }


}

class Bank {

    String name = "bank";
    public volatile int money = 0;
    //不加锁+性能高，局部微创
    AtomicIntegerFieldUpdater<Bank> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Bank.class, "money");

    public void transferBank(Bank bank) {
        atomicIntegerFieldUpdater.incrementAndGet(bank);
    }
}
