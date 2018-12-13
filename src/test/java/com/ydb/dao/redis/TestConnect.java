package com.ydb.dao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConnect {
    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void testConnection() {
        redisTemplate.opsForValue().set("stringKey","stringValue");
        System.out.println(redisTemplate.opsForValue().get("stringKey"));
    }
}
