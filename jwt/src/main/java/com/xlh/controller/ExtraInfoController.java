package com.xlh.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xlh.entity.ExtraInfo;
import com.xlh.mapper.ExtraInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: xielinhao
 * @title: ExtralnfoController
 * @projectName: hole
 * @description:
 * @date: 17:03 2021/9/13
 */

@Api(tags = "JSON测试")
@RestController
@RequestMapping("extra")
public class ExtraInfoController {

    @Resource
    private ExtraInfoMapper extraInfoMapper;

    @ApiOperation("测试")
    @GetMapping
    public JSONObject get(){
        List<ExtraInfo> extraInfos = this.extraInfoMapper.selectList(Wrappers.<ExtraInfo>lambdaQuery());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg",extraInfos);
        return jsonObject;
    }

}
