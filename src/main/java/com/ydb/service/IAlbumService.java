package com.ydb.service;

import com.ydb.bean.ResultBean;
import com.ydb.dao.IAlbumDao;
import com.ydb.entity.Admin;
import com.ydb.entity.Album;
import com.ydb.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;


public interface IAlbumService {
    //添加相册的信息
    ResultBean<Album> addPhoto(Album album);

    //删除相册，通过album_id删除
    ResultBean<Album> dropAlbum(Integer album_id);

    //修改相册
    ResultBean<Album> updateAlbum(Album album);

    //根据相册的id来查询相册
    ResultBean<Album> queryAlbumById(Integer album_id);

    //根据相册的name来查询相册
    ResultBean<Album> queryAlbumByName(String album_name);
}
