package com.xlh;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xlh.entity.User;
import com.xlh.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author: xielinhao
 * @title: SpringBootTest
 * @projectName: hole
 * @description:
 * @date: 15:36 2021/9/10
 */
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        User user=new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setPhone("13396635482");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    public void delete(){
        userMapper.deleteById("f8b68f9ba45f2980b3d71b51a371b1cc");
    }

    @Test
    public void select(){
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUuid, "f8b68f9ba45f2980b3d71b51a371b1cc"));
        System.out.println(user);
    }

}
