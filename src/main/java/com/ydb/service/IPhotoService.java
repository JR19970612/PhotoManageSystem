package com.ydb.service;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Photo;

public interface IPhotoService {

    //添加单张图片
    ResultBean<Photo> addPhoto(Photo photo);

    //通过photoId（编号）删除单张图片
    ResultBean<Photo> dropPhoto(Integer photoId);

    //通过photoId（编号）修改单张图片信息
    ResultBean<Photo> updatePhoto(Photo photo);

    //通过photoId（编号）查询单张图片
    ResultBean<Photo> queryPhoto(Integer photoId);

    //通过photoName（图片名）查询单张图片
    ResultBean<Photo> queryPhoto(String photoName);
}

