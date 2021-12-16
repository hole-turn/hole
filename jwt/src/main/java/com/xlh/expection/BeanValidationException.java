package com.xlh.expection;

import com.xlh.common.base.Result;

/**
 * @author: xielinhao
 * @title: BeanValidationException
 * @projectName: hole
 * @description:
 * @date: 16:52 2021/12/16
 */
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
