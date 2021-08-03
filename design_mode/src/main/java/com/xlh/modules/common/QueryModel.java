package com.xlh.modules.common;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * mybatis-plus查询实体类标记注解
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface QueryModel {

}
