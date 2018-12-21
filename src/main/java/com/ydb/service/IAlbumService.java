package com.ydb.service;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Album;

import java.util.List;


public interface IAlbumService {
    //添加相册的信息
    ResultBean<Album> addAlbum(Album album);

    //删除相册，通过album_id删除
    ResultBean<Album> dropAlbum(Integer album_id);

    //修改相册
    ResultBean<Album> updateAlbum(Album album);

    //根据相册的id来查询相册
    ResultBean<Album> queryAlbum(Integer album_id);

    //根据相册的name来查询相册
    ResultBean<Album> queryAlbum(String album_name);

    //分页查询相册
    ResultBean<List<Album>> queryAlbum(Integer pageSize, Integer pageNum);

    //查询所有相册
    ResultBean<List<Album>> queryAlbum();
}
