package com.xlh.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xielinhao
 * @title: RedisGeoTests
 * @projectName: hole
 * @description:
 * @date: 13:47 2022/7/28
 */
@SpringBootTest
public class RedisGeoTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void geoAdd() {
        Map<String, Point> map = new HashMap<>();
        map.put("天安门", new Point(116.403963, 39.915119));
        map.put("故宫", new Point(116.403414, 39.924091));
        map.put("长城", new Point(116.024067, 40.362639));
        stringRedisTemplate.opsForGeo().add("city", map);
        System.out.println(map.toString());
    }

    @Test
    public void geoGet() {
        //获取经纬度坐标
        List<Point> position = stringRedisTemplate.opsForGeo().position("city", "天安门");
        System.out.println(position);

        //geohash算法生成的base32编码值
        List<String> hash = stringRedisTemplate.opsForGeo().hash("city", "天安门");
        System.out.println(hash);

        //两地距离
        Distance distance = stringRedisTemplate.opsForGeo().distance("city", "天安门", "故宫", RedisGeoCommands.DistanceUnit.METERS);
        System.out.println(distance);

        //通过经度，纬度查找附近的
        //这个坐标是北京王府井位置
        Circle circle = new Circle(116.418017, 39.914402, Metrics.KILOMETERS.getMultiplier());
        //返回50条
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(50);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = stringRedisTemplate.opsForGeo().radius("city", circle, args);
        System.out.println(geoResults);

        //通过地方查找附近 半径10公里内
        RedisGeoCommands.GeoRadiusCommandArgs args2 = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(50);
        //半径10公里内
        Distance distance10 = new Distance(10, Metrics.KILOMETERS);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults2 = this.stringRedisTemplate.opsForGeo().radius("city", "天安门", distance10, args2);
        System.out.println(geoResults2);
    }

}
