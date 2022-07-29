package com.xlh.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author: xielinhao
 * @title: RedisHyperloglogTests
 * @projectName: hole
 * @description:
 * @date: 13:47 2022/7/28
 */
@SpringBootTest
public class RedisHyperloglogTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        stringRedisTemplate.opsForHyperLogLog().add("hyperloglog_key1:", "1", "2", "3", "4", "5");
        stringRedisTemplate.opsForHyperLogLog().add("hyperloglog_key2:", "1", "1", "6", "7", "8");

        Long key1Size = stringRedisTemplate.opsForHyperLogLog().size("hyperloglog_key1:");
        Long key2Size = stringRedisTemplate.opsForHyperLogLog().size("hyperloglog_key2:");

        Long union = stringRedisTemplate.opsForHyperLogLog().union("hyperloglog_key1:", "hyperloglog_key2:");
        System.out.println("hyperloglog_key1:" + key1Size);
        System.out.println("hyperloglog_key2:" + key2Size);
        System.out.println("unionSize:" + union);

    }
}
