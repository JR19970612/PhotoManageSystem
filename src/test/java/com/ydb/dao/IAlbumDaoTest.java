package com.ydb.dao;

import com.ydb.entity.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IAlbumDaoTest {

    @Autowired
    IAlbumDao albumDao;

    @Test
    public void insertAlbum() {
        Album album = new Album();
        album.setAlbumName("相册A");
        album.setAlbumDesc("相册描述");
        album.setAlbumCreatetime(new Date());
        albumDao.insertAlbum(album);
    }

    @Test
    public void deleteAlbum() {
    }

    @Test
    public void selectAllAlbum() {
        List<Album> albums = albumDao.selectAllAlbum();
        System.out.println(albums);
    }
}
