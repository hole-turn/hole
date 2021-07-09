package com.xlh.common.util;

import com.xlh.common.base.ResultCode;
import com.xlh.common.exception.BusinessException;
import com.xlh.common.response.Response;
import io.micrometer.core.instrument.util.StringUtils;

public class DataCheckUtils {

    public static void checkAnyParamIsEmpty(String string) {
        checkAnyParamIsEmpty(string, new String[]{});
    }

    public static void checkAnyParamIsEmpty(String string, String... params) {
        if (StringUtils.isBlank(string)) {
            throw new BusinessException(ResultCode.PARAM_MISS);
        }

        for (String param : params) {
            if (StringUtils.isBlank(param)) {
                throw new BusinessException(ResultCode.PARAM_MISS);
            }
        }
    }

    public static void checkAnyParamIsNull(Object param1, Object... params) {
        if (param1 == null) {
            throw new BusinessException(ResultCode.PARAM_MISS);
        }

        for (Object param : params) {
            if (param == null) {
                throw new BusinessException(ResultCode.PARAM_MISS);
            }
        }
    }

    public static void checkDataIsNull(Object object) {
        checkDataIsNull(object, null);
    }

    public static void checkDataIsNull(Object object, ResultCode resultEnum) {
        if (object == null) {
            if (resultEnum == null) {
                resultEnum = ResultCode.PARAM_MISS;
            }
            throw new BusinessException(resultEnum);
        }
    }

    public static void checkDataIsNull(Object object, Response response) {
        if (object == null) {
            if (response == null) {
                response = ResultCode.PARAM_MISS;
            }
            throw new BusinessException(response);
        }
    }
}
