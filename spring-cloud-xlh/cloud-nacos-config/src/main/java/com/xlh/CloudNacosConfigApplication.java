package com.xlh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: xielinhao
 * @title: CloudNacosConfigApplication
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 14:45 2022/10/21
 */
@SpringBootApplication
public class CloudNacosConfigApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CloudNacosConfigApplication.class, args);
//            String name = context.getEnvironment().getProperty("user.name");
//            String appname = context.getEnvironment().getProperty("app.name");
//            String age = context.getEnvironment().getProperty("user.age");
//            System.out.println("user name :" +name+"; age: "+age);
        String sdkappid = context.getEnvironment().getProperty("txcloud.video.sdkappid");
            String hostname = context.getEnvironment().getProperty("app.hostname");
            String appid = context.getEnvironment().getProperty("apppush.getui.appid");
//            String appname = context.getEnvironment().getProperty("app.name");
//            String age = context.getEnvironment().getProperty("user.age");
//            System.out.println("user name :" +name+"; age: "+age);
        System.out.println(sdkappid);
        System.out.println(hostname);
        System.out.println(appid);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
