package com.ydb;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkApplicationTests {
    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.matches("123", "$2a$10$UKN3X6OIKpekpq29J1X.H.Q4geMHf4qCDO7.xM2LNBUzO9D5GMfyC"));
    }

    @Test
    public void thum() throws IOException {
        File file = new File("E:\\相册\\Camera Roll\\333055-106.jpg");
        Thumbnails.of(file)
                .scale(0.25)
                .toFile("C:\\Users\\Administrator\\Desktop\\a380_200x300.jpg");
    }
}
