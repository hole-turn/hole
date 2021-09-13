package com.xlh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xlh.annotation.NoLogin;
import com.xlh.annotation.UserLoginToken;
import com.xlh.entity.User;
import com.xlh.mapper.UserMapper;
import com.xlh.service.TokenService;
import com.xlh.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: xielinhao
 * @title: UserController
 * @projectName: hole
 * @description:
 * @date: 16:03 2021/9/10
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenService tokenService;

    @ApiOperation("登陆")
    @PostMapping("login")
    public JSONObject login(@RequestBody UserVo vo) {
        JSONObject jsonObject = new JSONObject();
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, vo.getUsername()));
        if (user == null) {
            jsonObject.put("msg", "用户不存在");
        } else {
            if (!user.getPassword().equals(vo.getPassword())) {
                jsonObject.put("mes", "用户密码错误");
            } else {
                String token = tokenService.getToken(user);
                jsonObject.put("token", token);
                jsonObject.put("msg", "登陆成功");
            }
        }
        return jsonObject;

    }
    @UserLoginToken
    @ApiOperation("查询用户")
    @GetMapping("getOne")
    @ApiImplicitParam(paramType = "query", name = "uuid", value = "查询", required = true, dataType = "String")
    public JSONObject getOne(String uuid) {
        User user = userMapper.selectById(uuid);
        JSONObject jsonObject = new JSONObject();
        if (user != null) {
            jsonObject.put("username", user.getUsername());
            jsonObject.put("phone", user.getPhone());
            jsonObject.put("msg", "success!");

        } else {
            jsonObject.put("msg", "查询失败");
        }
        return jsonObject;
    }

    @ApiOperation("无需登录接口测试")
    @NoLogin
    @GetMapping
    public JSONObject get(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","测试成功");
        return jsonObject;
    }

}
