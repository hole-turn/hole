package com.xlh.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xlh.annotation.NoLogin;
import com.xlh.annotation.UserLoginToken;
import com.xlh.entity.User;
import com.xlh.mapper.UserMapper;
import com.xlh.service.UserService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: xielinhao
 * @title: AuthenticationInterceptor
 * @projectName: hole
 * @description:
 * @date: 17:33 2021/9/9
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        //从http 请求头中取出token
        String token = request.getHeader("Authorization");
        //如果不是映射到方法就跳过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        //检查是否有NoLogin注解，有就跳过认证
        if (method.isAnnotationPresent(NoLogin.class)){
            NoLogin annotation = method.getAnnotation(NoLogin.class);
            if (annotation.required()){
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()){
                //执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登陆");
                }
                // 获取 token 中的 user id
                String userId;
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }catch (Exception e){
                    throw new RuntimeException("401权限异常");
                }
                User user = userService.findUserById(userId);
                if (user==null){
                    throw new RuntimeException("用户不存在");
                }
                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUsername())).build();
                System.out.println(jwtVerifier.verify(token).getHeader());
                try{
                    jwtVerifier.verify(token);
                }catch (Exception e){
                    throw new RuntimeException("401");
                }
                return true;
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
