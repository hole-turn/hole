package com.xlh.modules.common;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 *mybatis-plus查询字段标注
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface QueryColumn {

    /**
     * 数据库表对应的字段名称
     *
     * @return
     */
    String column();

    /**
     * 查询类型，默认精确查询
     *
     * @return
     */
    QueryTypeEnum queryType() default QueryTypeEnum.EQ;

    enum QueryTypeEnum {

        /**
         * map 所有非空属性等于 =
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#allEq(Map)
         */
        ALL_EQ,

        /**
         * 等于
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#eq(Object, Object)
         * @see com.bwjf.store.support.EqQueryColumnHandler
         */
        EQ,

        /**
         * 不等于
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#ne(Object, Object)
         */
        NE,

        /**
         * 大于
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#gt(Object, Object)
         */
        GT,

        /**
         * 大于等于
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#ge(Object, Object)
         * @see com.bwjf.store.support.GeQueryColumnHandler
         */
        GE,

        /**
         * 小于
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#lt(Object, Object)
         */
        LT,

        /**
         * 小于等于
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#le(Object, Object)
         * @see com.bwjf.store.support.LeQueryColumnHandler
         */
        LE,

        /**
         * BETWEEN 值1 AND 值2
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#between(Object, Object, Object)
         */
        BETWEEN,

        /**
         * NOT BETWEEN 值1 AND 值2
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#notBetween(Object, Object, Object)
         */
        NOT_BETWEEN,

        /**
         * LIKE '%值%'
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#like(Object, Object)
         * @see com.bwjf.store.support.LikeQueryColumnHandler
         */
        LIKE,

        /**
         * NOT LIKE '%值%'
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#notLike(Object, Object)
         */
        NOT_LIKE,

        /**
         * LIKE '%值'
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#likeLeft(Object, Object)
         * @see com.bwjf.store.support.LikeLeftQueryColumnHandler
         */
        LIKE_LEFT,

        /**
         * LIKE '值%'
         *
         * @see com.baomidou.mybatisplus.core.conditions.query.QueryWrapper#likeRight(Object, Object)
         */
        LIKE_RIGHT

    }

}
