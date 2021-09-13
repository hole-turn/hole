package com.xlh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlh.entity.User;
import com.xlh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: xielinhao
 * @title: UserService
 * @projectName: hole
 * @description:
 * @date: 15:26 2021/9/10
 */
public interface UserService extends IService<User> {

    User findByUsername(User user);

    User findUserById(String userId);

}
