package com.xlh.modules.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.annotations.VisibleForTesting;
import com.xlh.modules.common.QueryColumn;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

/**
 * 将使用特定注解标注的实体类转换为{@link QueryWrapper}。支持的注解如下:
 * <ul>
 * <li>{@link QueryModel} 类使用该注解标注，会被转换为{@link QueryWrapper}</li>
 * <li>{@link SortColumn} 类使用该注解标注，会往{@link QueryWrapper}添加一个排序属性</li>
 * <li>{@link QueryColumn} 类属性使用该注解标注，会往{@link QueryWrapper}添加一个查询属性，
 * 目前支持{@link QueryColumn.QueryTypeEnum#EQ}、{@link QueryColumn.QueryTypeEnum#LIKE}、{@link QueryColumn.QueryTypeEnum#LE}、{@link QueryColumn.QueryTypeEnum#GE}</li>
 * <li>{@link QueryColumns} 类属性使用该注解标注，会往{@link QueryWrapper}添加一个多个查询属性</li>
 * </ul>
 *
 * @author fangkuangzhang
 * @date 2021/07/08 16:19
 * @since v0.3
 */
public class QueryWrapperFactory {

    private static final Map<Class<?>, QueryColumnFilter<?>> columnFilterMap = new HashMap<>();
    private static final Map<QueryColumn.QueryTypeEnum, QueryColumnHandler> columnHandlerMap = new HashMap<>();

    static {
        StringQueryColumnFilter stringFilter = new StringQueryColumnFilter();
        columnFilterMap.put(stringFilter.validateType(), stringFilter);

        loadQueryColumnHandler();
    }

    /**
     * 使用SPI加载{@link QueryColumnHandler}
     */
    private static void loadQueryColumnHandler() {
        ServiceLoader<QueryColumnHandler> loader = ServiceLoader.load(QueryColumnHandler.class);
        for (QueryColumnHandler queryColumnHandler : loader) {
            columnHandlerMap.put(queryColumnHandler.supportType(), queryColumnHandler);
        }
    }

    public static Map<QueryColumn.QueryTypeEnum, QueryColumnHandler> getColumnHandlerMap() {
        return columnHandlerMap;
    }

    /**
     * 创建{@link QueryWrapper}
     *
     * @param <T>
     * @param params
     * @return
     * @throws IllegalArgumentException 如果实体类为<code>null</code>或者未使用注解{@link QueryModel}标注
     */
    @SuppressWarnings("unchecked")
    public static <T> QueryWrapper<T> createQueryWrapper(Object params) {
        if (params == null) {
            throw new IllegalArgumentException("参数params不能为空");
        }

        Class<?> clazz = params.getClass();
        QueryModel queryModelAnnotation = clazz.getAnnotation(QueryModel.class);
        if (queryModelAnnotation == null) {
            throw new IllegalArgumentException("非法的参数，实体类: " + clazz.getName() + "没有用注解@QueryModel标注");
        }

        QueryWrapper<?> queryWrapper = new QueryWrapper<>();
        dealClassAnnotation(clazz, queryWrapper);
        dealFieldAnnotation(params, clazz, queryWrapper);
        return (QueryWrapper<T>) queryWrapper;
    }

    private static void dealFieldAnnotation(Object params, Class<?> clazz, QueryWrapper<?> queryWrapper) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fillQueryColumn(queryWrapper, params, field);
        }
    }

    private static void dealClassAnnotation(Class<?> clazz, QueryWrapper<?> queryWrapper) {
        SortColumn sortColumnAnnotation = clazz.getAnnotation(SortColumn.class);
        if (sortColumnAnnotation != null) {
            fillSortColumn(queryWrapper, sortColumnAnnotation);
        }
    }

    private static void fillSortColumn(QueryWrapper<?> queryWrapper, SortColumn sortColumnAnnotation) {
        String column = sortColumnAnnotation.column();
        SortColumn.SortTypeEnum sortTypeEnum = sortColumnAnnotation.sortType();
        if (sortTypeEnum == SortColumn.SortTypeEnum.ASC) {
            queryWrapper.orderByAsc(column);
        } else if (sortTypeEnum == SortColumn.SortTypeEnum.DESC) {
            queryWrapper.orderByDesc(column);
        }
    }

    /**
     * 根据字段上的 {@link QueryColumn} 或 {@link QueryColumns} 注解来填充 {@link QueryWrapper}
     *
     * @param queryWrapper
     * @param obj          参数实例
     * @param field        参数实例上的属性
     * @return true {@link Field} 的值被添加到 {@link QueryWrapper} ; false {@link Field} 的值被忽略
     */
    @SneakyThrows
    @VisibleForTesting
    protected static boolean fillQueryColumn(QueryWrapper<?> queryWrapper, Object obj, Field field) {
        QueryColumn queryColumnAnnotion = field.getAnnotation(QueryColumn.class);
        QueryColumns queryColumnsAnnotion = field.getAnnotation(QueryColumns.class);
        if (queryColumnAnnotion == null && queryColumnsAnnotion == null) {
            return false;
        }
        if (queryColumnAnnotion != null && queryColumnsAnnotion != null) {
            throw new IllegalArgumentException("注解@QueryColumn和@QueryColumns不能标注在同一个Field上!");
        }

        field.setAccessible(true);
        Object fieldValue = field.get(obj);
        if (fieldValue == null) return false;

        Class<?> fieldClass = fieldValue.getClass();
        QueryColumnFilter<Object> columnFilter = getColumnFilter(fieldClass);

        if (!columnFilter.filter(fieldValue)) {
            List<QueryColumn> queryColumnList = getQueryColumns(queryColumnAnnotion, queryColumnsAnnotion);
            if (queryColumnList.size() == 1) {
                QueryColumn queryColumn = queryColumnList.get(0);
                doHandle(queryWrapper, queryColumn, fieldValue, 0);
            } else if (queryColumnList.size() > 1) {
                queryWrapper.and(generateQueryWrapperHandle(queryColumnList, fieldValue));
            }
            return true;
        }
        return false;
    }

    private static <T> Function<QueryWrapper<T>, QueryWrapper<T>> generateQueryWrapperHandle(List<QueryColumn> queryColumnList, Object fieldValue) {
        return queryWrapper -> {
            for (int i=0; i<queryColumnList.size(); i++) {
                QueryColumn queryColumn = queryColumnList.get(i);
                doHandle(queryWrapper, queryColumn, fieldValue, i);
            }
            return queryWrapper;
        };

    }

    private static <T> void doHandle(QueryWrapper<T> queryWrapper, QueryColumn queryColumn, Object fieldValue, int index) {
        String column = queryColumn.column();
        QueryColumn.QueryTypeEnum queryType = queryColumn.queryType();
        QueryColumnHandler queryColumnHandler = columnHandlerMap.get(queryType);
        if (queryColumnHandler != null) {
            if (index == 0) {
                queryColumnHandler.handle(queryWrapper, column, fieldValue);
            } else {
                queryColumnHandler.handle(queryWrapper.or(), column, fieldValue);
            }
        }
    }

    /**
     * 根据注解 {@link QueryColumn} 和 {@link QueryColumns} 获取 {@link QueryColumn} 集合
     * @param queryColumnAnnotion
     * @param queryColumnsAnnotion
     * @return 如果入参都为空，则返回空集合
     */
    private static List<QueryColumn> getQueryColumns(QueryColumn queryColumnAnnotion, QueryColumns queryColumnsAnnotion) {
        if (queryColumnAnnotion != null) {
            return Collections.singletonList(queryColumnAnnotion);
        }
        if (queryColumnsAnnotion != null) {
            return Arrays.asList(queryColumnsAnnotion.value());
        }
        return Collections.emptyList();
    }

    /**
     * 根据字段类型获取字段过滤器
     *
     * @param fieldClass
     * @return 不为空
     */
    @SuppressWarnings("unchecked")
    @VisibleForTesting
    protected static QueryColumnFilter<Object> getColumnFilter(Class<?> fieldClass) {
        QueryColumnFilter<Object> queryColumnFilter = (QueryColumnFilter<Object>) columnFilterMap.get(fieldClass);
        return queryColumnFilter != null ? queryColumnFilter : DefaultQueryColumnFilter.getInstance();
    }

}
