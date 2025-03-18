package org.example.dessertshopspringboot.Utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil implements ApplicationContextAware {
    private static StringRedisTemplate stringRedisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 在实现 ApplicationContextAware 接口的方法中获取 StringRedisTemplate 实例
        stringRedisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    }
    public static String get(String Key) {
        return stringRedisTemplate.opsForValue().get(Key);
    }

    public static void set(String Key,String Value,Integer ExpireTime,TimeUnit TimeUnit) {
        stringRedisTemplate.opsForValue().set(Key,Value,ExpireTime,TimeUnit);
    }


    public static void delete(String key){
        stringRedisTemplate.delete(key);
    }
}
