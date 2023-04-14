package com.xlh.pay.core.client;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.xlh.pay.common.ErrorCode;
import com.xlh.pay.common.PayFrameworkErrorCodeConstants;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

/**
 * 支付的 PayCommonResult 拓展类
 * 考虑到不同的平台，返回的 code 和 msg 是不同的，所以统一额外返回 {@link #apiCode} 和 {@link #apiMsg} 字段
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class PayCommonResult<T> {

    /**
     * API 返回错误码
     * <p>
     * 由于第三方的错误码可能是字符串，所以使用 String 类型
     */
    private String apiCode;
    /**
     * API 返回提示
     */
    private String apiMsg;

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示
     */
    private String msg;

    private PayCommonResult() {
    }

    public static <T> PayCommonResult<T> build(String apiCode, String apiMsg, T data, AbstractPayCodeMapping codeMapping) {
        Assert.notNull(codeMapping, "参数 codeMapping 不能为空");
        PayCommonResult<T> result = new PayCommonResult<T>().setApiCode(apiCode).setApiMsg(apiMsg);
        result.setData(data);
        // 翻译错误码
        if (codeMapping != null) {
            ErrorCode errorCode = codeMapping.apply(apiCode, apiMsg);
            result.setCode(errorCode.getCode()).setMsg(errorCode.getMsg());
        }
        return result;
    }

    public static <T> PayCommonResult<T> error(Throwable ex) {
        PayCommonResult<T> result = new PayCommonResult<>();
        result.setCode(PayFrameworkErrorCodeConstants.EXCEPTION.getCode());
        result.setMsg(ExceptionUtil.getRootCauseMessage(ex));
        return result;
    }

}
