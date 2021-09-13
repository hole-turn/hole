package com.xlh.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlh.entity.User;
import com.xlh.mapper.UserMapper;
import com.xlh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: xielinhao
 * @title: UserServiceImpl
 * @projectName: hole
 * @description:
 * @date: 15:27 2021/9/10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;
    public User findByUsername(User user){
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
    }
    public User findUserById(String userId) {
        return userMapper.selectById(userId);
    }
}
