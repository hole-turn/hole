package com.xlh.modules.common;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * mybatis-plus查询字段标注, 支持一个字段映射数据库多个列
 * 测试不完全
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface QueryColumns {

    /**
     * 一个字段映射数据库多个列, 多个字段使用or连接
     * @return
     */
    QueryColumn[] value();

}