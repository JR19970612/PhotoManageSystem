package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Photo;
import com.ydb.service.IPhotoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController

public class PhotoController {
    @Autowired
    IPhotoService photoService;


    @ApiOperation(value = "上传单张图片信息")
    @PostMapping(value = "/photo")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Photo> uploadPhoto(MultipartHttpServletRequest request, @Validated Photo photo) throws IOException {
        return photoService.addPhoto(request, photo);
    }


    @ApiOperation(value = "删除单张图片信息")
    @DeleteMapping(value = "/photo/{photoId}")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Photo> dropPhoto(@PathVariable Integer photoId) {
        return photoService.dropPhoto(photoId);
    }


    @ApiOperation(value = "修改单张图片信息")
    @PutMapping(value = "/photo")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Photo> updatePhoto(@Validated Photo photo, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return photoService.updatePhoto(photo);
    }

    @ApiOperation(value = "获取图片信息", notes = "通过photoId(编号)或名称获取用户")
    @GetMapping(value = "/photo/{type}")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Photo> getPhotoByType(@PathVariable String type, String params) {
        if (type != null & type.equals("photoId")) {
            return photoService.queryPhoto(Integer.valueOf(params));
        } else if (type != null & type.equals("photoName")) {
            return photoService.queryPhoto(params);
        } else {
            throw new RuntimeException("非法的查询方式");
        }
    }
//    @GetMapping(value = "/photo/{params}")
//    public ResultBean<Photo> getPhotoByType(@PathVariable String params) {
//        try {
//            return photoService.queryPhoto(Integer.parseInt(params));
//        } catch (NumberFormatException e) {
//            return photoService.queryPhoto(params);
//        }
//    }

}
