package org.example.dessertshopspringboot.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dessertshopspringboot.Utils.JwtUtil;
import org.example.dessertshopspringboot.Utils.RedisUtil;
import org.example.dessertshopspringboot.Utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptors implements HandlerInterceptor {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //验证令牌
        String token=request.getHeader("Authorization");
        try {
            Map<String,Object> claims= JwtUtil.parseToken(token);
            String redisToken= RedisUtil.get("username"+claims.get("username").toString());
            if(redisToken==null || redisToken.isEmpty()||!redisToken.equals(token)){
                throw new RuntimeException();
            }
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e){
            //设置响应码
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove();
    }
}
