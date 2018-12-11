package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Photo;
import com.ydb.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    IPhotoService photoService;

    //根据photoId(编号)或名称查询单张图片
    @GetMapping(value = "/{type}")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Photo> getPhotoByType(@PathVariable String type, String params) {
        ResultBean<Photo> resultBean = null;
        if (type != null & type.equals("photoId")) {
            resultBean = photoService.queryPhoto(Integer.valueOf(params));
        } else if (type != null & type.equals("photoName")) {
            resultBean = photoService.queryPhoto(params);
        } else {
            throw new RuntimeException("非法的查询方式");
        }
        return resultBean;
    }

    //上传单张图片信息
    @PostMapping(value = "/photo")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Photo> uploadPhoto(MultipartHttpServletRequest request, Photo photo) throws IOException {
        return photoService.addPhoto(request, photo);
    }

    //根据photo_id删除单张图片信息
    @DeleteMapping(value = "/photo/{photoId}")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Photo> dropPhoto(@PathVariable Integer photoId) {
        return photoService.dropPhoto(photoId);
    }

    //修改单张图片信息
    @PutMapping(value = "/photo")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Photo> updatePhoto(Photo photo) {
        return photoService.updatePhoto(photo);
    }


}
