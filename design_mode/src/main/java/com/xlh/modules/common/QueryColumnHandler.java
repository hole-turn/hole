package com.xlh.modules.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author: xielinhao
 * @title: QueryColumnHandler
 * @projectName: hole
 * @description:查询字段处理器
 * @date: 17:37 2021/8/3
 */
public interface QueryColumnHandler {

    /**
     * 支持的查询类型
     *
     * @return
     */
    QueryColumn.QueryTypeEnum supportType();

    /**
     * 根据查询类型进行相应的处理
     *
     * @param queryWrapper
     * @param column
     * @param fieldValue
     */
    void handle(QueryWrapper<?> queryWrapper, String column, Object fieldValue);
}