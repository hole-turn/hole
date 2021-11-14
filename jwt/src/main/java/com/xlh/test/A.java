package com.xlh.test;

import lombok.Data;

import java.util.List;

/**
 * @author: xielinhao
 * @title: A
 * @projectName: hole
 * @description:
 * @date: 13:46 2021/10/25
 */
@Data
public class A {
    private List<String> list;
    private String name;
    private B b;
}
