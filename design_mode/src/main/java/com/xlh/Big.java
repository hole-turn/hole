package com.xlh;

import java.math.BigDecimal;

/**
 * @author: xielinhao
 * @title: Big
 * @projectName: hole
 * @description:
 * @date: 14:47 2021/7/30
 */
public class Big {
    public static void main(String[] args) {
        BigDecimal a=new BigDecimal(10);
//        BigDecimal b=new BigDecimal(20);
////        BigDecimal subtract = a.subtract(b);
////        System.out.println(subtract);
////        int i = subtract.compareTo(BigDecimal.ZERO);
////        System.out.println(i);
//        boolean boo = a.subtract(b).compareTo(BigDecimal.ZERO) < 0;
//        System.out.println(boo);
        BigDecimal negate = a.negate();
        System.out.println(negate);
    }
}
