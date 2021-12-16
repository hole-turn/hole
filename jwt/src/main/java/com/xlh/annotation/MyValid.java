package com.xlh.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = {MyValidator.class})
public @interface MyValid {


    int min() default 0;

    int max() default Integer.MAX_VALUE;

    /**
     * 是否强制校验
     *
     * @return 是否强制校验的boolean值
     */
    boolean required() default true;

    /**
     * 校验不通过时的报错信息
     *
     * @return 校验不通过时的报错信息
     */
    String message() default "参数错误";

    /**
     * 将validator进行分类，不同的类group中会执行不同的validator操作
     *
     * @return validator的分类类型
     */
    Class<?>[] groups() default {};

    /**
     * 主要是针对bean，很少使用
     *
     * @return 负载
     */
    Class<? extends Payload>[] payload() default {};
}
