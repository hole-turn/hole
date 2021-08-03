package com.xlh.modules.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author: xielinhao
 * @title: LeQueryColumnHandler
 * @projectName: hole
 * @description:处理{@link QueryWrapper#le(Object, Object)}
 * @date: 17:40 2021/8/3
 */
public class LeQueryColumnHandler implements QueryColumnHandler {

    @Override
    public QueryColumn.QueryTypeEnum supportType() {
        return QueryColumn.QueryTypeEnum.LE;
    }

    @Override
    public void handle(QueryWrapper<?> queryWrapper, String column, Object fieldValue) {
        queryWrapper.le(column, fieldValue);
    }

}
