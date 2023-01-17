package com.xlh.test;

import lombok.Data;

/**
 * @author: xielinhao
 * @title: WebHookDto
 * @projectName: internethospital
 * @description:
 * @date: 16:14 2022/8/23
 */
@Data
public class WebHookDto {
    private String msgtype = "text";

    private WebHookText text;
}
