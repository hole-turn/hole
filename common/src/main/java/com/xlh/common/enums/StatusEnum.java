package com.xlh.common.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    NORMAL(1, "正常"),
    FORBIDDEN(2, "禁用");

    private Integer code;
    private String message;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }
}