package com.xlh.redis;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: xielinhao
 * @title: RedisAppicationTests
 * @projectName: hole
 * @description:
 * @date: 10:05 2022/8/4
 */
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void tests(){

    }

}
