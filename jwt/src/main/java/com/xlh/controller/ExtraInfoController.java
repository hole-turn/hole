package com.xlh.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xlh.common.base.Result;
import com.xlh.entity.ExtraInfo;
import com.xlh.mapper.ExtraInfoMapper;
import com.xlh.vo.ExtraInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
@Validated
@RequestMapping("extra")
public class ExtraInfoController {

    @Resource
    private ExtraInfoMapper extraInfoMapper;

    @ApiOperation("测试")
    @GetMapping
    public JSONObject get() {
        List<ExtraInfo> extraInfos = this.extraInfoMapper.selectList(Wrappers.<ExtraInfo>lambdaQuery());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", extraInfos);
        return jsonObject;
    }


    @ApiOperation("新增")
    @PostMapping("add")
    public Result add(@RequestBody @Valid ExtraInfoVO extraInfoVO) {
//        ExtraInfo extraInfo = BeanUtil.toBean(extraInfoVO, ExtraInfo.class);
//        extraInfoMapper.insert(extraInfo);

        return Result.success();
    }

}
