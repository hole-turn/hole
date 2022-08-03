package com.xlh.redis;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: xielinhao
 * @title: BloomFilterTests
 * @projectName: hole
 * @description:
 * @date: 16:53 2022/7/29
 */
@SpringBootTest
public class BloomFilterTests {

    public static final int _1W = 10000;

    //布隆过滤器里预计要插入多少数据
    public static int size = 100 * _1W;
    //误判率,它越小误判的个数也就越少
    public static double fpp = 0.03;

    @Autowired
    private RedissonClient redissonClient;

    RBloomFilter rBloomFilter = null;


    @Test
    public void bloomTest1() {
        rBloomFilter = redissonClient.getBloomFilter("phoneListBloomFilter", new StringCodec());
        rBloomFilter.tryInit(size, fpp);
        // 1测试  布隆过滤器有+redis有
        rBloomFilter.add("10001");
        redissonClient.getBucket("10001", new StringCodec()).set("chinamobile10001");
        System.out.println(getPhoneListById("10001"));
    }

    @Test
    public void bloomTest2() {
        rBloomFilter = redissonClient.getBloomFilter("phoneListBloomFilter", new StringCodec());
        rBloomFilter.tryInit(size, fpp);
        //  2测试  布隆过滤器有+redis无
        rBloomFilter.add("10087");
        System.out.println(getPhoneListById("10087"));
    }

    @Test
    public void bloomTest3() {
        rBloomFilter = redissonClient.getBloomFilter("phoneListBloomFilter", new StringCodec());
        rBloomFilter.tryInit(size, fpp);
        //3 测试 ，都没有
        System.out.println(getPhoneListById("10089"));
    }

    private String getPhoneListById(String IDNumber) {
        String result = null;

        if (IDNumber == null) {
            return null;
        }
        //1 先去布隆过滤器里面查询
        if (rBloomFilter.contains(IDNumber)) {
            //2 布隆过滤器里有，再去redis里面查询
            RBucket<String> rBucket = redissonClient.getBucket(IDNumber, new StringCodec());
            result = rBucket.get();
            if (result != null) {
                return "i come from redis: " + result;
            } else {
                result = getPhoneListByMySQL(IDNumber);
                if (result == null) {
                    return null;
                }
                // 重新将数据更新回redis
                redissonClient.getBucket(IDNumber, new StringCodec()).set(result);
            }
            return "i come from mysql: " + result;
        }
        return result;
    }

    private String getPhoneListByMySQL(String IDNumber) {
        return "chinamobile" + IDNumber;
    }


}
