package org.example.dessertshopspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        ValueOperations<String,String> valueOperations= stringRedisTemplate.opsForValue();
        valueOperations.set("hello","world");
        valueOperations.set("hello2","world2",10, TimeUnit.SECONDS);

    }

    @Test
    public void testGet() {
        ValueOperations<String,String>valueOperations= stringRedisTemplate.opsForValue();
        System.out.println(valueOperations.get("hello"));
    }
}
