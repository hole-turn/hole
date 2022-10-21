package com.xlh.test;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.getui.push.v2.sdk.ApiHelper;
import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.api.PushApi;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.req.Audience;
import com.getui.push.v2.sdk.dto.req.Settings;
import com.getui.push.v2.sdk.dto.req.message.PushChannel;
import com.getui.push.v2.sdk.dto.req.message.PushDTO;
import com.getui.push.v2.sdk.dto.req.message.PushMessage;
import com.getui.push.v2.sdk.dto.req.message.android.AndroidDTO;
import com.getui.push.v2.sdk.dto.req.message.android.GTNotification;
import com.getui.push.v2.sdk.dto.req.message.android.ThirdNotification;
import com.getui.push.v2.sdk.dto.req.message.android.Ups;
import com.getui.push.v2.sdk.dto.req.message.ios.Alert;
import com.getui.push.v2.sdk.dto.req.message.ios.Aps;
import com.getui.push.v2.sdk.dto.req.message.ios.IosDTO;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @author: xielinhao
 * @title: G
 * @projectName: hole
 * @description:
 * @date: 10:16 2022/9/14
 */
public class G {
    public static void main(String[] args) {
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        //填写应用配置，参数在“Uni Push”下的“应用配置”页面中获取
        apiConfiguration.setAppId("CBmFvngeT28RL9B8TqXiQ");
        apiConfiguration.setAppKey("7yIU6rpdOD6thNjipnnSm9");
        apiConfiguration.setMasterSecret("Ji31NrphlK6T2Gr7uQc5D8");
//        apiConfiguration.setDomain("https://restapi.getui.com/v2/");
        // 实例化ApiHelper对象，用于创建接口对象
        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        // 创建对象，建议复用。目前有PushApi、StatisticApi、UserApi
        PushApi pushApi = apiHelper.creatApi(PushApi.class);
        //根据cid进行单推
        PushDTO<Audience> pushDTO = new PushDTO<Audience>();
        // 设置推送参数，requestid需要每次变化唯一
        pushDTO.setRequestId(System.currentTimeMillis() + "");
        Settings settings = new Settings();
        pushDTO.setSettings(settings);
        //消息有效期，走厂商消息必须设置该值
        settings.setTtl(-1);

        //在线走个推通道时推送的消息体
        PushMessage pushMessage = new PushMessage();
        pushDTO.setPushMessage(pushMessage);
        GTNotification gtNotification = new GTNotification();
        gtNotification.setTitle("这里是标题");
        gtNotification.setBody("这里是消息体");
        gtNotification.setClickType("payload");
        HashMap<Object, Object> map = Maps.newHashMap();
//        map.put("pathType", pushDto.getPathType());
//        map.put("url", pushDto.getUrl());
//        map.put("args", pushDto.getArgs());
//        map.put("title", pushDto.getTitle());
//        map.put("content", pushDto.getBody());
        String s = JSONObject.toJSONString(map);
        gtNotification.setPayload(s);
        pushMessage.setNotification(gtNotification);
        // 设置接收人信息
        Audience audience = new Audience();
        pushDTO.setAudience(audience);
        audience.addCid("5839b376168e0e0adb31716da8c84b33");
        //设置离线推送时的消息体
        PushChannel pushChannel = new PushChannel();
        //安卓离线厂商通道推送的消息体
        AndroidDTO androidDTO = new AndroidDTO();
        Ups ups = new Ups();
        ThirdNotification thirdNotification = new ThirdNotification();
        ups.setNotification(thirdNotification);
        thirdNotification.setTitle("安卓离线展示的标题");
        thirdNotification.setBody("安卓离线展示的内容");
        thirdNotification.setClickType("intent");
        //注意：intent参数必须按下方文档（特殊参数说明）要求的固定格式传值，intent错误会导致客户端无法收到消息
        thirdNotification.setIntent("intent://io.dcloud.unipush/?#Intent;scheme=unipush;launchFlags=0x4000000;component=uni.UNIE7D72C1/io.dcloud.PandoraEntry;S.UP-OL-SU=true;S.title=测试标题;S.content=测试内容;S.payload=test;end");
        androidDTO.setUps(ups);
        pushChannel.setAndroid(androidDTO);

        //ios离线apn通道推送的消息体
        Alert alert = new Alert();
        alert.setTitle("苹果离线通知栏标题");
        alert.setBody("苹果离线通知栏内容");
        Aps aps = new Aps();
        aps.setContentAvailable(0);
        aps.setSound("default");
        aps.setAlert(alert);
        IosDTO iosDTO = new IosDTO();
        iosDTO.setAps(aps);
        iosDTO.setType("notify");
        pushChannel.setIos(iosDTO);

        pushDTO.setPushChannel(pushChannel);

        // 进行cid单推
        ApiResult<Map<String, Map<String, String>>> apiResult = pushApi.pushToSingleByCid(pushDTO);
        if (apiResult.isSuccess()) {
            System.out.println("chengg");
            // success
            System.out.println(apiResult.getData());
        } else {
            // failed
            System.out.println("code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
        }
    }

}
