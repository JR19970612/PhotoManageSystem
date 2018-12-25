package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.PhotoView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Album;
import com.ydb.entity.Photo;
import com.ydb.exception.ParamsException;
import com.ydb.service.imp.PhotoServiceImp;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@CrossOrigin
@RestController
public class PhotoController {
    @Autowired
    PhotoServiceImp photoService;


    @ApiOperation(value = "上传单张图片信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "photo", value = "上传图片文件", required = true, paramType = "form", dataType = "File"),
            @ApiImplicitParam(name = "photoName", value = "图片名", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "photoDesc", value = "图片描述", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "albumId", value = "相册ID", required = true, paramType = "form", dataType = "String"),
    }
    )
    @PostMapping(value = "/photo", params = {"photoName", "photoDesc", "albumId"})
    @JsonView(PhotoView.QueryRoughly.class)
    public ModelAndView uploadPhoto(MultipartHttpServletRequest request, Photo photo) throws IOException {
        ResultBean resultBean = photoService.addPhoto(request, photo);
        ModelAndView modelAndView = new ModelAndView("redirectPhotoView", "status", resultBean.getStatus());
        return modelAndView;
    }


    @ApiOperation(value = "删除单张图片信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "photoId", value = "图片ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "photoName", value = "图片名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "photoOriginalUrl", value = "图片原图URL", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "photoThumUrl", value = "图片缩略图URL", required = true, paramType = "query", dataType = "String"),
    }
    )
    @DeleteMapping(value = "/photo", params = {"photoId", "photoName", "photoOriginalUrl", "photoThumUrl"})
    @JsonView(PhotoView.QueryRoughly.class)
    public ResultBean<Photo> dropPhoto(Photo photo) {
        return photoService.dropPhoto(photo);
    }


    @ApiOperation(value = "修改单张图片信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "photoId", value = "图片ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "photoName", value = "图片名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "photoDesc", value = "图片描述", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "albumId", value = "相册ID", required = false, paramType = "query", dataType = "int"),
    }
    )
    @PutMapping(value = "/photo", params = {"photoId"})
    @JsonView(PhotoView.QueryRoughly.class)
    /*public ResultBean<Photo> updatePhoto(Photo photo) {
        return photoService.updatePhoto(photo);
    }*/
    public ModelAndView updatePhoto(Photo photo) throws IOException {
        ResultBean<Photo> resultBean = iAlbumService.updatePhoto(photo);
        ModelAndView modelAndView = new ModelAndView("redirectAlbumView", "status", resultBean.getStatus());
        return modelAndView;
    }

    @ApiOperation(value = "获取单张图片信息", notes = "通过photoId(编号)或名称获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "查询形式（可选择photoName|photoId）", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "params", value = "查询参数", required = true, paramType = "query", dataType = "String"),
    }
    )
    @GetMapping(value = "/photo/{type}", params = "params")
    @JsonView(PhotoView.QueryDetail.class)
    public ResultBean<Photo> getPhotoByType(@PathVariable String type, String params) {
        if (type != null & type.equals("photoId")) {
            return photoService.queryPhoto(Integer.valueOf(params));
        } else if (type != null & type.equals("photoName")) {
            return photoService.queryPhoto(params);
        } else {
            throw new ParamsException("非法的查询方式");
        }
    }


    @ApiOperation(value = "获取图片信息", notes = "获取分页图片信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "当前页面", required = true, paramType = "path", dataType = "int"),
    }
    )
    @GetMapping(value = "/photo/{pageSize}/{pageNum}")
    @JsonView(PhotoView.QueryRoughly.class)
    public ResultBean<Photo> getAllPhoto(@PathVariable Integer pageSize, @PathVariable Integer pageNum) {
        return photoService.queryPhoto(pageSize, pageNum);
    }

}
