package com.xlh.common.exception;


import com.xlh.common.base.Result;

public class BeanValidationException extends RuntimeException {
    private static final long serialVersionUID = -9090364364631575687L;
    private Result<Object> result;

    public BeanValidationException(Result<Object> result) {
        this.result = result;
    }

    public Result<Object> getResult() {
        return this.result;
    }

    @Override
    public String getMessage() {
        return result.getMsg();
    }
}
