package com.xlh.controller;

import com.xlh.common.base.Result;
import com.xlh.service.RetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: xielinhao
 * @title: RetryController
 * @projectName: holeturn
 * @description:
 * @date: 13:28 2022/4/12
 */
@RestController
@Api(tags = "重试模块")
@RequestMapping("retry")
public class RetryController {

    @Autowired
    private RetryService retryService;


    @ApiOperation("请求")
    @GetMapping("get")
    public Result get(@RequestParam("uuid") String uuid){
        return retryService.get(uuid);
    }


}
