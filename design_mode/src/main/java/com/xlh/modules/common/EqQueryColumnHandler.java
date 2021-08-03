package com.xlh.modules.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author: xielinhao
 * @title: EqQueryColumnHandler
 * @projectName: hole
 * @description:处理{@link QueryWrapper#eq(Object, Object)}
 * @date: 17:39 2021/8/3
 */
public class EqQueryColumnHandler implements QueryColumnHandler {

    @Override
    public QueryColumn.QueryTypeEnum supportType() {
        return QueryColumn.QueryTypeEnum.EQ;
    }

    @Override
    public void handle(QueryWrapper<?> queryWrapper, String column, Object fieldValue) {
        queryWrapper.eq(column, fieldValue);
    }

}