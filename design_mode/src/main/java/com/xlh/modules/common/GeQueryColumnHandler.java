package com.xlh.modules.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author: xielinhao
 * @title: GeQueryColumnHandler
 * @projectName: hole
 * @description:处理{@link QueryWrapper#ge(Object, Object)}
 * @date: 17:39 2021/8/3
 */
public class GeQueryColumnHandler implements QueryColumnHandler {

    @Override
    public QueryColumn.QueryTypeEnum supportType() {
        return QueryColumn.QueryTypeEnum.GE;
    }

    @Override
    public void handle(QueryWrapper<?> queryWrapper, String column, Object fieldValue) {
        queryWrapper.ge(column, fieldValue);
    }

}