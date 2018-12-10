package com.ydb.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IPhotoDaoTest {
    @Autowired
    IPhotoDao photoDao;

    @Test
    public void insertPhoto() {
        System.out.println(photoDao.getClass().getName());
    }

    @Test
    public void deletePhoto() {
    }

    @Test
    public void updatePhoto() {
    }

    @Test
    public void selectPhotoByName() {
    }

    @Test
    public void selectPhotoById() {
    }
}
