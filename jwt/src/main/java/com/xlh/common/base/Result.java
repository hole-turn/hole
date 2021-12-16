package com.xlh.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.io.Serializable;

/**
 * @author: xielinhao
 * @title: Result
 * @projectName: hole
 * @description:
 * @date: 16:54 2021/12/16
 */
@Builder
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    @ApiModelProperty("承载数据")
    private T data;

    @ApiModelProperty(value = "返回消息",required = true)
    private String msg;

    private Result(){
        this(ResultCode.SUCCESS);
    }

    private Result(ResultCode resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private Result(ResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Result(ResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private Result(ResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Result<T> data(T data) {
        return data(data, "操作成功");
    }

    public static <T> Result<T> data(T data, String msg) {
        return data(200, data, msg);
    }

    public static <T> Result<T> data(int code, T data, String msg) {
        return new Result(code, data, data == null ? "暂无承载数据" : msg);
    }

    public static <T> Result<T> success() {
        return new Result(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(String msg) {
        return new Result(ResultCode.SUCCESS, msg);
    }

    public static <T> Result<T> success(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public static <T> Result<T> success(ResultCode resultCode, String msg) {
        return new Result(resultCode, msg);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result(ResultCode.FAILURE, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result(code, (Object)null, msg);
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public static <T> Result<T> fail(ResultCode resultCode, String msg) {
        return new Result(resultCode, msg);
    }

    public static <T> Result<T> status(boolean flag) {
        return flag ? success("操作成功") : fail("操作失败");
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }


}
