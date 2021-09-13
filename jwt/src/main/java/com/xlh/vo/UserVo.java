package com.xlh.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: xielinhao
 * @title: UserVo
 * @projectName: hole
 * @description:
 * @date: 16:05 2021/9/10
 */
@Data
public class UserVo {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

}
