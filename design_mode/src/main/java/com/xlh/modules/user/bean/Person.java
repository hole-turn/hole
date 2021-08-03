package com.xlh.modules.user.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author: xielinhao
 * @title: Person
 * @projectName: hole
 * @description:
 * @date: 17:28 2021/8/3
 */
@Data
@TableName("person")
public class Person extends Model<Person> {
    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String name;

    private Integer age;

    private Integer sex;
}
