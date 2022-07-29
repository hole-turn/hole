package com.xlh.redis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Maps;
import com.xlh.redis.utils.RedisServiceExtend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class RedisBitMapApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisServiceExtend redisServiceExtend;

    @Test
    void contextLoads() {
        long l = redisServiceExtend.bitCount("20220727");
        Long aLong = redisServiceExtend.bitCount("20220727", 0, 1);
        System.out.println(l);
        System.out.println(aLong);

        Long and = redisServiceExtend.bitOp(RedisStringCommands.BitOperation.AND, "needSaveKey", "20220727", "20220728");
        long needSaveKey = redisServiceExtend.bitCount("needSaveKey");
        System.out.println(and);
        System.out.println(needSaveKey);
    }

    @Test
    public void testRedis() {
        stringRedisTemplate.opsForValue().setBit("20220727", 0, true);
        stringRedisTemplate.opsForValue().setBit("20220727", 1, true);
        stringRedisTemplate.opsForValue().setBit("20220727", 2, true);
        stringRedisTemplate.opsForValue().setBit("20220727", 8, true);

        stringRedisTemplate.opsForValue().setBit("20220728", 0, true);
        stringRedisTemplate.opsForValue().setBit("20220728", 1, true);

        Boolean bit = stringRedisTemplate.opsForValue().getBit("20220727", 0);
        System.out.println(bit);
    }


    @Test
    public void testRedisADD() {
        stringRedisTemplate.opsForValue().setBit("user:sign:slf:202207", 23, true);
        stringRedisTemplate.opsForValue().setBit("user:sign:slf:202207", 22, true);
        stringRedisTemplate.opsForValue().setBit("user:sign:slf:202207", 21, true);
        stringRedisTemplate.opsForValue().setBit("user:sign:slf:202207", 20, true);

    }

    //签到
    @Test
    public void qiandao() {
        Date date = new Date();
        //偏移量
        int offset = DateUtil.dayOfMonth(date) - 1;
        String redisKey = buildKey("slf", date);
        // 查看是否签到
        Boolean sign = stringRedisTemplate.opsForValue().getBit(redisKey, offset);

        //签到
        if (!sign) {
            stringRedisTemplate.opsForValue().setBit(redisKey, offset, true);
        }

    }

    //统计连续签到的次数
    @Test
    public void lianxuqiandao() {
        Date date = new Date();

        int dayOfMonth = DateUtil.dayOfMonth(date);

        String redisKey = buildKey("slf", date);

        //获取本月截止今天为止的所有签到记录
        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth)).valueAt(0);
        List<Long> longs = stringRedisTemplate.opsForValue().bitField(redisKey, bitFieldSubCommands);

        if (CollectionUtil.isEmpty(longs)) {
            System.out.println("这个月连续签到的天数为0");
            return;
        }

        Long signCount = longs.get(0);

        if (signCount == null || signCount == 0) {
            System.out.println("这个月连续签到的天数为0");
            return;
        }

        System.out.println("这个返回的是十进制数字" + signCount);

        int count = 0;

        //循环遍历
        while (true) {
            //将这个数字与1做与运算,得到数字的最后一个bit位
            if ((signCount & 1) == 0) {
                break;
            } else {
                //不是0说明已签到 连续签到+1
                count++;
            }
            //将数字右移一位，抛弃最后一个bit位，继续下一个bit位
            signCount >>>= 1;
        }

        System.out.println("这个月连续签到的天数" + count);
    }

    //查询用户当月签到日历
    @Test
    public void lianxuqiandaoCalendar() {

        Date date = new Date();

        //当前月的最后一天
        int endDayOfMoney = DateUtil.endOfMonth(date).dayOfMonth();


        Map<String, Boolean> map = Maps.newLinkedHashMap();

        String redisKey = buildKey("slf", date);

        //获取本月截止今天为止的所有签到记录
        BitFieldSubCommands bitFieldSubCommands = BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(endDayOfMoney)).valueAt(0);
        List<Long> longs = stringRedisTemplate.opsForValue().bitField(redisKey, bitFieldSubCommands);

        if (CollectionUtil.isEmpty(longs)) {
            return;
        }

        Long signCount = longs.get(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < endDayOfMoney; i++) {

            DateTime dateTime = DateUtil.offsetDay(DateUtil.endOfMonth(date).toJdkDate(), - i);

            map.put(dateFormat.format(dateTime), signCount >> 1 << 1 != signCount);

            signCount >>= 1;
        }

        map.forEach((key, value) -> System.out.println(key + "  " + value));
    }

    private String buildKey(String username, Date date) {
        return String.format("user:sign:%s:%s", username,
                DateUtil.format(date, "yyyyMM"));
    }

}
