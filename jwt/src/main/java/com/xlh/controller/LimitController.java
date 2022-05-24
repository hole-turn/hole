package com.xlh.controller;

import com.xlh.annotation.limit.RateLimiter;
import com.xlh.common.base.Result;
import com.xlh.enums.LimitType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xielinhao
 * @title: LimitController
 * @projectName: holeturn
 * @description:
 * @date: 14:39 2022/5/24
 */
@RestController
@Api(tags = "接口限流api")
@RequestMapping("limit")
public class LimitController {

    @ApiOperation("限制访问")
    @GetMapping
    @RateLimiter(time = 5,count = 3,limitType = LimitType.IP)
    public Result limit(){
        return Result.success();
    }

    @ApiOperation("模拟抢票限制30s总访问次数限制10张")
    @GetMapping("qiang")
    @RateLimiter(time = 30,count = 10,limitType = LimitType.DEFAULT)
    public Result qiang(){
        return Result.success();
    }
}
