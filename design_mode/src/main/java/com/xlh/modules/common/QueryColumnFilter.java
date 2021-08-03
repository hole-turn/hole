package com.xlh.modules.common;
/**
 *字段值过滤器
 */
public interface QueryColumnFilter<T> {

    /**
     * 支持的校验类型
     * @return
     */
    Class<T> validateType();

    /**
     * 根据字段的值判断是否需要过滤该字段
     * @param t 字段的值
     * @return true - 需要过滤该字段(该字段的值不会添加到{@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper})
     */
    boolean filter(T t);

}
