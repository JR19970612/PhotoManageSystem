package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Album;
import com.ydb.exception.ParamsException;
import com.ydb.service.IAlbumService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {

    @Autowired
    IAlbumService iAlbumService;


    @ApiOperation(value = "添加相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumName", value = "相册名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "albumDesc", value = "相册描述", required = true, paramType = "query", dataType = "String"),

    }
    )
    @PostMapping("/Album")
    @JsonView(SuccessView.class)
    public ResultBean<Album> addAlbum(@PathVariable Album album) {
        return iAlbumService.addAlbum(album);
    }

    @ApiOperation(value = "删除相册信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册ID", required = true, paramType = "path", dataType = "int"),
    }
    )
    @DeleteMapping(value = "/Album/{album_id}")
    @JsonView(SuccessView.class)
    public ResultBean<Album> dropAlbum(@PathVariable Integer album_id) {
        return iAlbumService.dropAlbum(album_id);
    }


    @ApiOperation(value = "更新相册信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "albumId", value = "相册ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "albumName", value = "相册名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "albumDesc", value = "相册描述", required = true, paramType = "query", dataType = "String"),
    }
    )
    @PutMapping("/Album")
    @JsonView(SuccessView.class)
    public ResultBean<Album> updateAlbum(@PathVariable Album album) {
        return iAlbumService.updateAlbum(album);
    }


    @ApiOperation(value = "查询指定相册信息", notes = "通过相册的id或名称来查询相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "搜索类型，可以通过根据ID(AlbumId）或名称（AlbumName）方式查询，注意该参数填写在路径上", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "params", value = "查询参数", required = true, paramType = "query", dataType = "String"),
    }
    )
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


    @ApiOperation(value = "查询所有相册信息")
    @GetMapping("/Album")
    @JsonView(SuccessView.class)
    public ResultBean<Album> getAllAlbum() {
        return iAlbumService.queryAllAlbum();
    }


}
