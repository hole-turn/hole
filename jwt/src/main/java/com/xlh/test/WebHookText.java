package com.xlh.test;

import lombok.Data;

import java.util.List;

/**
 * @author: xielinhao
 * @title: WebHookText
 * @projectName: internethospital
 * @description:
 * @date: 16:15 2022/8/23
 */
@Data
public class WebHookText {

    private String content;

    private List<String> mentioned_mobile_list;
}
