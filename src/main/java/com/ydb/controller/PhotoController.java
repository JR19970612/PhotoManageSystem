package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Photo;
import com.ydb.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        int i=1/0;
        return resultBean;
    }


}
