package com.xlh.common.util;

import java.util.UUID;

/**
 * @author: xielinhao
 * @title: UUIDUtils
 * @date: 14:05 2021/7/1
 */
public class UUIDUtils {
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String defaultUuid(){
        return "0";
    }
}
