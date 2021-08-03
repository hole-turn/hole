package com.xlh.modules.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: xielinhao
 * @title: LikeQueryColumnHandler
 * @projectName: hole
 * @description:处理{@link QueryWrapper#like(Object, Object)}
 * @date: 17:40 2021/8/3
 */
@Slf4j
public class LikeQueryColumnHandler implements QueryColumnHandler {

    @Override
    public QueryColumn.QueryTypeEnum supportType() {
        return QueryColumn.QueryTypeEnum.LIKE;
    }

    @Override
    public void handle(QueryWrapper<?> queryWrapper, String column, Object fieldValue) {
        if (fieldValue instanceof String) {
            String strFieldValue = (String) fieldValue;
            queryWrapper.like(column, strFieldValue.trim());
        } else {
            log.warn("QueryColumnHandler [{}] unsupport field type [{}]", this.getClass(), fieldValue.getClass());
        }
    }

}
