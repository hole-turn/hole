package com.xlh.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: xielinhao
 * @title: RedisServiceExtend
 * @projectName: hole
 * @description:
 * @date: 10:49 2022/7/27
 */
@Component
public class RedisServiceExtend {
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static String redisCode = "utf-8";

    public long bitCount(final String key) {
        return stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }

    public Long bitCount(String key, int start, int end) {
        return stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
    }

    public Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        return stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
    }
}
