package com.xlh;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xlh.entity.TCustomerDataEntity;
import com.xlh.entity.TMeetingDataEntity;
import com.xlh.entity.User;
import com.xlh.mapper.TCustomerDataMapper;
import com.xlh.mapper.THospitalMeetingDataMapper;
import com.xlh.mapper.TMeetingDataMapper;
import com.xlh.mapper.UserMapper;
import com.xlh.service.TMeetingDataService;
import com.xlh.util.DateRandom;
import com.xlh.util.ShareCodeUtil;
import com.xlh.util.TotalUtil;
import com.xlh.vo.HospitalMeetingVo;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    @Resource
    private TMeetingDataMapper meetingDataMapper;

    @Resource
    private TMeetingDataService meetingDataService;
    @Resource
    private TCustomerDataMapper customerDataMapper;

    @Resource
    private THospitalMeetingDataMapper hospitalMeetingDataMapper;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setPhone("13396635482");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    public void delete() {
        userMapper.deleteById("f8b68f9ba45f2980b3d71b51a371b1cc");
    }

    @Test
    public void select() {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUuid, "f8b68f9ba45f2980b3d71b51a371b1cc"));
        System.out.println(user);
    }

    @Test
    public void testtt() {
//        HospitalMeetingVo random = hospitalMeetingDataMapper.getRandom();
//        System.out.println(random.getHospitalName());
//        System.out.println(random.getHospitalLevel());
        String name = hospitalMeetingDataMapper.getName();
        System.out.println(name);
    }

    @Test
    public void insert() {

        int n = RandomUtil.randomInt(280, 400);
//        method(450,3600,"2021-09-01","2021-09-30");
        method(278,2920,"2021-10-01", "2021-10-31");


//        method(n, 559, "2020-09-01", "2020-09-30");
//        method(n, 532, "2020-10-01", "2020-10-31");
//        method(n, 581, "2020-11-01", "2020-11-30");
//        method(n, 564, "2020-12-01", "2020-12-31");
//        method(n, 560, "2021-01-01", "2021-1-31");
//        method(n, 574, "2021-02-01", "2021-02-28");
//        method(n, 586, "2021-03-01", "2021-03-31");
//        method(n, 575, "2021-04-01", "2021-04-30");
//        method(n, 576, "2021-05-01", "2021-05-31");
//        method(n, 577, "2021-06-01", "2021-06-30");
//        method(n, 580, "2021-07-01", "2021-07-31");
//        method(n, 559, "2021-08-01", "2021-08-31");
//        method(n, 560, "2021-09-01", "2021-09-30");
//        method(n, 563, "2021-10-01", "2021-10-31");

    }

    private void method(int n, int sum, String startDate, String endDate) {
        ArrayList<Integer> integers = TotalUtil.splitTotal(sum, n, 6, 15);

        for (int i = 0; i < n; i++) {

            TMeetingDataEntity entity = new TMeetingDataEntity();

            //随机生成九月份数据
            Date date = DateRandom.getDateRandom(startDate, endDate);

            //周六周日排除
            if (DateUtil.dayOfWeek(date) == 1 && DateUtil.dayOfMonth(date) <= 2) {
                Calendar calendar = DateUtil.calendar(date);
                calendar.add(Calendar.DATE, +1);
                date = calendar.getTime();
            } else if (DateUtil.dayOfWeek(date) == 1) {
                Calendar calendar = DateUtil.calendar(date);
                calendar.add(Calendar.DATE, -2);
                date = calendar.getTime();
            }
            if (DateUtil.dayOfWeek(date) == 7 && DateUtil.dayOfMonth(date) <= 1) {
                Calendar calendar = DateUtil.calendar(date);
                calendar.add(Calendar.DATE, +3);
                date = calendar.getTime();
            } else if (DateUtil.dayOfWeek(date) == 7) {
                Calendar calendar = DateUtil.calendar(date);
                calendar.add(Calendar.DATE, -1);
                date = calendar.getTime();
            }

            //随机生成结束时间
            int timeNumRandom = DateRandom.getTimeNumRandom();
            Calendar calendar = DateUtil.calendar(date);
            calendar.add(Calendar.MINUTE, timeNumRandom);
            Date endTime = calendar.getTime();

            //随机生成会议时间
            Time time = Time.valueOf(DateRandom.minuteToHHMMSS(timeNumRandom));

            //随机生成医院 级别
            HospitalMeetingVo random = hospitalMeetingDataMapper.getRandom();

            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int integer = 0; integer < integers.get(i); integer++) {

                String name = hospitalMeetingDataMapper.getName();
                sb.append(name).append(",");

                Integer id = hospitalMeetingDataMapper.getId();
                sb2.append(id).append(",");
            }
            String s = sb.toString();
            String substring = s.substring(0, s.length() - 1);
            String string = sb2.toString();
            String sub = string.substring(0, string.length() - 1);

            entity.setMeetingDate(date)
                    .setMeetingDateStart(date)
                    .setCreateTime(date)
                    .setUpdateTime(date)
                    .setMeetingDateEnd(endTime)
                    .setMeetTime(time)
                    .setHospitalLevel(random.getHospitalLevel())
                    .setHospitalTarget(random.getHospitalName())
                    .setMeetingNum(integers.get(i))
                    .setCostSettle(new BigDecimal(300).multiply(new BigDecimal(integers.get(i))))
                    .setIntegral(300 * integers.get(i))
                    .setMeetingMembers(substring)
                    .setMeetingMemberIds(sub)
                    .setServiceObj("西藏康馨药业有限公司")

            ;

            meetingDataMapper.insert(entity);


        }
    }

    @Test
    public void sort() {
        List<TMeetingDataEntity> tMeetingDataEntities = meetingDataMapper.selectList(Wrappers.<TMeetingDataEntity>lambdaQuery().orderByAsc(TMeetingDataEntity::getCreateTime));
        AtomicInteger i = new AtomicInteger();
        tMeetingDataEntities.stream().forEach(s -> {
            i.getAndIncrement();
            s.setMeetId(String.valueOf(i.get()));
        });
        meetingDataService.saveOrUpdateBatch(tMeetingDataEntities);
    }


    @Test
    public void customer() {
        List<TCustomerDataEntity> tCustomerDataEntities = customerDataMapper.selectList(Wrappers.<TCustomerDataEntity>lambdaQuery().isNull(TCustomerDataEntity::getCreateTime));
//        List<TCustomerDataEntity> tCustomerDataEntities = customerDataMapper.selectList(null);

        tCustomerDataEntities.stream().forEach(s -> {
            s.setCreateTime(DateRandom.getDateRandom("2021-10-01", "2021-10-31"));
            s.setOrigin(RandomUtil.randomInt(0, 3));
            s.updateById();
        });
    }

}
