package com.xlh.modules.common;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * mybatis-plus排序字段标注
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface SortColumn {

    /**
     * 数据库表对应的字段名称
     *
     * @return
     */
    String column();

    /**
     * 排序方式，默认使用倒序
     *
     * @return
     */
    SortTypeEnum sortType() default SortTypeEnum.DESC;

    enum SortTypeEnum {

        /**
         * 正序
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#orderByAsc(Object)
         */
        ASC,

        /**
         * 倒序
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#orderByDesc(Object)
         */
        DESC

    }

}
