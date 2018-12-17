package com.ydb.dao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConnect {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    WebApplicationContext applicationContext;

    @Test
    public void testConnection() {
        redisTemplate.opsForValue().set("stringKey", "stringValue");
        System.out.println(redisTemplate.opsForValue().get("stringKey"));
    }

    @Test
    public void testHasKey() {
        System.out.println("hasKey"+redisTemplate.keys("photo:photoId-*photoName-Name_A"));
    }
}
