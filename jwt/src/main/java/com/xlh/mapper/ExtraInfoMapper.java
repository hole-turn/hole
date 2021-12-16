package com.xlh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlh.entity.ExtraInfo;

import java.util.List;

/**
 * @author: xielinhao
 * @title: ExtraInfoMapper
 * @projectName: hole
 * @description:
 * @date: 17:02 2021/9/13
 */
public interface ExtraInfoMapper extends BaseMapper<ExtraInfo> {
    List<ExtraInfo> getInfo();
}
