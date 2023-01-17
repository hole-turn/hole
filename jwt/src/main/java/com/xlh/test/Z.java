package com.xlh.test;

import com.xlh.test.WebHookText;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * @author: xielinhao
 * @title: Z
 * @projectName: spring-cloud-xlh
 * @description:
 * @date: 13:25 2022/12/21
 */
@Data
public class Z {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime applyTime = now.plusMinutes(-2);
        System.out.println(now);
        System.out.println(applyTime);
        System.out.println(now);

    }
}

