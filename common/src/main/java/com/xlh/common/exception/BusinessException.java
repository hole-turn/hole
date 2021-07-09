package com.xlh.common.exception;


import com.xlh.common.base.ResultCode;
import com.xlh.common.response.Response;

public class BusinessException extends RuntimeException {

    /**
     * 自定义错误码
     */
    private Integer code;

    public BusinessException() {
        this(ResultCode.FAILURE);
    }

    /**
     * 自定义构造器，只保留一个，让其必须输入错误码及内容
     */
    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(String msg) {
        this(ResultCode.FAILURE.getCode(), msg);
    }

    /**
     * 自定义构造器，只保留一个，让其必须输入错误码及内容
     */
    public BusinessException(ResultCode resultEnum) {
        this(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException(Response response) {
        this(response.getCode(), response.getMsg());
    }

}
