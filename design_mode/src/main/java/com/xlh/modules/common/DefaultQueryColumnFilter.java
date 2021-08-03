package com.xlh.modules.common;

/**
 * @author: xielinhao
 * @title: DefaultQueryColumnFilter
 * @projectName: hole
 * @description: 对字段不进行过滤，当没有找到指定的类型的过滤器，使用不过滤策略
 * @date: 17:37 2021/8/3
 */
public class DefaultQueryColumnFilter implements QueryColumnFilter<Object> {

    private static final DefaultQueryColumnFilter INSTANCE = new DefaultQueryColumnFilter();

    private DefaultQueryColumnFilter() {
    }

    public static DefaultQueryColumnFilter getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<Object> validateType() {
        return Object.class;
    }

    @Override
    public boolean filter(Object o) {
        return false;
    }

}