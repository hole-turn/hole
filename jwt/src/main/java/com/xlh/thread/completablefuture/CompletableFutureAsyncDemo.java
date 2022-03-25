package com.xlh.thread.completablefuture;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: xielinhao
 * @title: CompletableFutureAsyncDemo
 * @projectName: holeturn
 * @description:
 * @date: 11:00 2022/3/25
 */
public class CompletableFutureAsyncDemo {

    public static List<Mall> mallList = new ArrayList<>();
    static {
        mallList=Arrays.asList(
//                new Mall("one"),
                new Mall("ane"),
                new Mall("bne"),
                new Mall("cne"),
                new Mall("dne"),
                new Mall("ene"),
                new Mall("fne"),
                new Mall("gne"),
                new Mall("hne"),
                new Mall("ine"),
                new Mall("jne"),
                new Mall("kne")
        );
    }



    public static void main(String[] args) {

        long start1 = System.currentTimeMillis();
        List<String> noAsyncList = getPriceList("about java");
        noAsyncList.forEach(System.out::println);
        long end1 = System.currentTimeMillis();
        System.out.println("耗时==>"+(end1-start1));


        long start2 = System.currentTimeMillis();
        List<String> listAsync = getListAsync("about java");
        listAsync.forEach(System.out::println);
        long end2 = System.currentTimeMillis();
        System.out.println("耗时==>"+(end2-start2));

    }

    private static List<String> getListAsync(String productName){
       return mallList.stream().map(s -> CompletableFuture.supplyAsync(() -> String.format(productName + " %s price is %.2f",s.getMallName(),s.getPriceByName(productName))))
               .collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static List<String> getPriceList(String productName) {
       return mallList.stream().map(s -> String.format(productName + " %s price is %.2f", s.getMallName(), s.getPriceByName(productName))).collect(Collectors.toList());
    }
}
@AllArgsConstructor
@Data
class Mall{

    private String mallName;

    public double getPriceByName(String productName){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() +productName.charAt(0);
    }

}
