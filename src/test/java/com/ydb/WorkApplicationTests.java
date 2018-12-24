package com.ydb;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.Random;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class WorkApplicationTests {
//    @Autowired
    WebApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        Random random=new Random();
        for (int i = 0; i < 16; i++) {
            System.out.println("http://localhost:8080/gdpi/favicon/" + random.nextInt(16) + ".bmp");
        }
    }

    @Test
    public void thum() throws IOException {
        File file = new File("E:\\相册\\Camera Roll\\333055-106.jpg");
        Thumbnails.of(file)
                .scale(0.25)
                .toFile("C:\\Users\\Administrator\\Desktop\\a380_200x300.jpg");
    }
}
