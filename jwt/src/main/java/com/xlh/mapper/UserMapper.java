package com.xlh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlh.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: xielinhao
 * @title: UserMapper
 * @projectName: hole
 * @description:
 * @date: 15:20 2021/9/10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
