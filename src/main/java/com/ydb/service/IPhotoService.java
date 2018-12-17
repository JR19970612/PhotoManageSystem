package com.ydb.service;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Photo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;


public interface IPhotoService {

    //添加单张图片信息
    @Transactional(rollbackFor = Exception.class)
    ResultBean<Photo> addPhoto(MultipartHttpServletRequest request, Photo photo) throws IOException;

    //通过photoId（编号）删除单张图片信息
    ResultBean<Photo> dropPhoto(Photo photo);

    //通过photoId（编号）修改单张图片信息
    ResultBean<Photo> updatePhoto(Photo photo);

    //通过photoId（编号）查询单张图片信息

    ResultBean<Photo> queryPhoto(Integer photoId);

    //通过photoName（图片名）查询单张图片信息
    ResultBean<Photo> queryPhoto(String photoName);

    //查询全部图片信息
    ResultBean<List<Photo>> queryPhoto();
}

