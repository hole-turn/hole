package com.xlh.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * @author: xielinhao
 * @title: ExtraNode
 * @projectName: hole
 * @description:
 * @date: 17:01 2021/9/13
 */
@Data
public class ExtraNode implements Serializable {
    private Integer id;
    @NotBlank(message = "名字不能为空")
    private String name;
}
