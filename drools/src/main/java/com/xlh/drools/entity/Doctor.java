package com.xlh.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: xielinhao
 * @title: Doctor
 * @projectName: hole
 * @description:
 * @date: 10:44 2022/8/1
 */
@Data
@AllArgsConstructor
public class Doctor {

    private String name;
    private Integer type;
    private Boolean full;
    private LocalDateTime createTime;
}
