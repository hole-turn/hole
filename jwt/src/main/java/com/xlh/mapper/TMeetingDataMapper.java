package com.xlh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlh.entity.TMeetingDataEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会议内容数据表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-14 12:28:40
 */
@Mapper
public interface TMeetingDataMapper extends BaseMapper<TMeetingDataEntity> {
	
}
