package com.xlh.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: xielinhao
 * @title: Person
 * @projectName: hole
 * @description:
 * @date: 10:08 2022/8/1
 */
@Data
@AllArgsConstructor
public class Person {
    private String name;
    private Integer age;
    // 是否可以玩游戏，此字段的值，由 drools 引擎计算得出
    private Boolean canPlayGame;
}
