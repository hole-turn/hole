package com.xlh.thread.lock.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: Lock8Demo
 * @projectName: holeturn
 * @description:
 * @date: 14:58 2022/4/2
 */
public class Lock8Demo {
    /*
     *1 标准访问有ab两个线程，请问先打印短信还是邮件
     *2 sendEmail方法暂停3s，请问先打印短信还是邮件
     *3 新增一个普通的hello方法，请问先打印hello还是邮件
     *4 有两部手机，请问先打印短信还是邮件
     *5 有两个静态同步方法，同一部手机，请问先打印短信还是邮件
     *6 有两个静态同步方法，2部手机，请问先打印短信还是邮件
     *7 1个静态同步方法，1个普通同步方法，同一部手机，请问先打印短信还是邮件
     *8 1个静态同步方法，1个普通同步方法，2部手机，请问先打印短信还是邮件
     *
     * 1-2解答：一个对象里面如果有多个synchronized方法，在某一时刻，只要有一个线程去调用其中的一个synchronized方法
     * 其他的线程都只能等待，换句话说，在某一时刻内，只能有唯一的一个线程去访问这些synchronized方法，
     * 锁的是当前对象this，被锁定后，其他的线程都不能进入到当前对象的其他synchronized方法
     * 3-4解答：加个普通方法后发现和同步锁无关
     * 换成两个对象后，不是同一把锁了，情况立刻变化
     * 5-6解答：对于普通同步方法，锁的是当前实例对象，通常指this，具体的一部部手机，所有的普通同步方法用的都是同一把锁--实例对象本身
     * 对于静态同步方法，锁的是当前类的Class，如Phone.class 唯一的一个模板
     * 对于同步方法块，锁的是synchronized括号里的对象
     * 7-8解答：当一个线程试图访问同步代码时它首先必须获得锁，退出或抛出异常时必须抛出锁
     * 所有的普通方法用的都是同一把锁——实例对象本身就是new出来的具体实例对象本身，本类this
     * 也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获得锁的方法释放锁后才能获取锁
     * 所有的静态同步方法用的都是同一把锁——类对象本身，就是我们说过的唯一模板Class
     * 具体实例对象this和唯一模板Class，这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞态条件的
     * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放释放锁后才能获取锁
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
//            phone.sendEmail();
            phone.sendStaticEmail();
        }, "a").start();

        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
//            phone.sendSms();
//            phone.hello();
            phone2.sendSms();
//            phone2.sendStaticSms();
        }, "b").start();
    }
}

class Phone {
    public synchronized void sendEmail() {
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("---sendEmail");
    }

    public synchronized void sendSms() {
        System.out.println("---sendSms");
    }
    public static synchronized void sendStaticEmail() {
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("---sendStaticEmail");
    }

    public static synchronized void sendStaticSms() {
        System.out.println("---sendStaticSms");
    }

    public void hello(){
        System.out.println("---hello");

    }
}
