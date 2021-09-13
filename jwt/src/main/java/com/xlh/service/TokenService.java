package com.xlh.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xlh.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author: xielinhao
 * @title: TokenService
 * @projectName: hole
 * @description:
 * @date: 16:09 2021/9/10
 */
@Service
public class TokenService {
    public String getToken(User user){
        String token="";
            token= JWT.create().withAudience(user.getUuid())// 将 user id 保存到 token 里面
        .sign(Algorithm.HMAC256(user.getUsername()));// 以 username 作为 token 的密钥
        return token;
    }
}
