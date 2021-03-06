package com.ydb.dao;

import com.ydb.entity.Photo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class


IPhotoDaoTest {
    @Autowired
    IPhotoDao photoDao;

    Photo photo = new Photo();

    @Before
    public void init() {
        photo.setPhotoId(52);
        photo.setPhotoName("Name_A");
        photo.setPhotoDesc("Desc_A");
        photo.setPhotoCreateTime(new Date());
        photo.setAlbumId(2);
        photo.setPhotoOriginalUrl("http://localhost");
        photo.setPhotoThumUrl("http://localhost");
    }

    @Test
    public void insertPhoto() {
        photoDao.insertPhoto(this.photo);
        System.out.println(photo);
    }

    @Test
    public void deletePhoto() {
        Integer status = photoDao.deletePhoto(photo);
        System.out.println("受影响列：" + status);
    }

    @Test
    public void updatePhoto() {
        photo.setPhotoId(9);
        photo.setPhotoName("图片");
        Integer status = photoDao.updatePhoto(photo);
        System.out.println("受影响列：" + status + "修改后的数据：" + photo);
    }

    @Test
    public void selectPhotoById() {
        List<Photo> photo = photoDao.selectPhotoById(1);
        System.out.println(photo);
    }

    @Test
    public void selectPhotoByName() {
        List<Photo> photo = photoDao.selectPhotoByName("图片名1");
        System.out.println(photo);
    }

    @Test
    public void selectAllPhoto() {
        List<Photo> photos = photoDao.selectAllPhoto();
        System.out.println(photos);
    }
}
