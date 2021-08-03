package com.xlh.modules.common;
import org.apache.commons.lang3.StringUtils;

/**
 *查询字段为{@link String}需要对<code>null</code>及空字符串的字段值过滤
 */
public class StringQueryColumnFilter implements QueryColumnFilter<String> {

    @Override
    public Class<String> validateType() {
        return String.class;
    }

    @Override
    public boolean filter(String s) {
        return StringUtils.isBlank(s);
    }

}
