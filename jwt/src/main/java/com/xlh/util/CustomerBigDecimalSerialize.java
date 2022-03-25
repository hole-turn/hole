package com.xlh.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author: xielinhao
 * @title: CustomerBigDecimalSerialize
 * @projectName: internethospital
 * @description:
 * @date: 15:00 2022/3/23
 */
public class CustomerBigDecimalSerialize extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (Objects.nonNull(value)) {
            gen.writeNumber(value.stripTrailingZeros());
            //去除0后缀,如果想统一进行保留精度，也可以采用类似处理
        } else {//如果为null的话，就写null
            gen.writeNull();
        }
    }
}
