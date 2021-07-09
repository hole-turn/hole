package com.xlh.common.base;


import com.xlh.common.response.Response;

public enum ResultCode implements Response {

    SUCCESS(200, "操作成功"),
    FAILURE(400, "业务异常"),
    UN_AUTHORIZED(401, "请求未授权或登录已过期"),
    NOT_FOUND(404, "404 没找到请求"),
    MSG_NOT_READABLE(400, "消息不能读取"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),
    REQ_REJECT(403, "请求被拒绝"),
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    PARAM_MISS(400, "缺少必要的请求参数"),
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),
    PARAM_VALID_ERROR(400, "参数校验失败"),
    PARAMS_CONVERSION_EXCEPTION(400, "参数转换异常"),
    NO_AUTHORITY(400, "用户无权限访问"),

    ACCOUNT_ALREADY_EXIST(600, "账号已存在"),
    USERNAME_ALREADY_EXIST(601, "用户名已存在"),
    MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION(700, "上传文件大小超过限制"),
    DATA_INTERGRITY_VIOLATION_EXCEPTION(800, "数据完整性违反异常"),

    FEILE_NOT_FOUND(404, "404 没找到请求");

    final int code;
    final String message;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return message;
    }
}
