package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Album;
import com.ydb.exception.ParamsException;
import com.ydb.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {

    @Autowired
    IAlbumService iAlbumService;

    //通过相册的id或名称来查询相册
    @GetMapping(value = "/album/{type}")
    @JsonView(SuccessView.class)
    public ResultBean<Album> getAlbumById(@PathVariable String type, @RequestParam String params) {
        if (type != null & type.equals("AlbumId")) {
            return iAlbumService.queryAlbumById(Integer.parseInt(params));
        } else if (type != null & type.equals("AlbumName")) {
            return iAlbumService.queryAlbumByName(params);
        } else {
            throw new ParamsException("非法的查询方式");
        }
    }

    //添加相册
    @GetMapping("/Album")
    @JsonView(SuccessView.class)
    public ResultBean<Album> getAllAlbum(@PathVariable Album album) {
        return iAlbumService.queryAllAlbum();
    }

    //添加相册
    @PostMapping("/Album")
    @JsonView(SuccessView.class)
    public ResultBean<Album> addAlbum(@PathVariable Album album) {
        return iAlbumService.addAlbum(album);
    }

    //删除相册
    @DeleteMapping(value = "/Album/{album_id}")
    @JsonView(SuccessView.class)
    public ResultBean<Album> dropAlbum(@PathVariable Integer album_id) {
        return iAlbumService.dropAlbum(album_id);
    }


    //修改相册
    @PutMapping("/Album")
    @JsonView(SuccessView.class)
    public ResultBean<Album> updateAlbum(@PathVariable Album album) {
        return iAlbumService.updateAlbum(album);
    }

}
