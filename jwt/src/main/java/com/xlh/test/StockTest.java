package com.xlh.test;

import com.google.common.collect.Maps;
import com.xlh.test.model.Stocks;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author: xielinhao
 * @title: StockTest
 * @projectName: holeturn
 * @description:
 * @date: 15:25 2022/4/25
 */
public class StockTest {

    public static void main(String[] args) {

        List<Stocks> need = new ArrayList<>();

        List<Stocks> stocks = Arrays.asList(
                new Stocks("A123", "A", new BigDecimal("12")),
                new Stocks("A456", "A", new BigDecimal("17")),
                new Stocks("B456", "B", new BigDecimal("4"))
        );


        for (Stocks stock : stocks) {


        }


    }


}
