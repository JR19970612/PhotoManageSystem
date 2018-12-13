package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Album;
import com.ydb.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {

    @Autowired
    IAlbumService iAlbumService;

    //根据相册的id来查询相册
    @GetMapping(value = "/albumById/{id}")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Album> getAlbumById(@PathVariable String id){
        if(id!=null&&id!=""){
            return iAlbumService.queryAlbumById(Integer.parseInt(id));
        }else{
            throw new RuntimeException("非法查询");
        }

    }
    //根据相册的name(名字)查询相册
    @GetMapping(value = "/albumByName/{name}")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Album> getAlbumByName(@PathVariable String name){
        if(name!=null&&name!=""){
            return iAlbumService.queryAlbumByName(name);
        }else{
            throw new RuntimeException("非法查询");
        }

    }

    //添加相册
    @PostMapping("/addAlbum")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Album> addAlbum(@PathVariable Album album){
        return iAlbumService.addAlbum(album);
    }

    //删除相册
    @DeleteMapping(value ="/dropAlbum/{album_id}")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Album> dropAlbum(@PathVariable Integer album_id){
        return iAlbumService.dropAlbum(album_id);

    }


    //修改相册
    @PutMapping("/updateAlbum")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Album> updateAlbum(@PathVariable Album album){
        return iAlbumService.updateAlbum(album);
    }










    //测试用的
    @RequestMapping("/hello.do")
    public String hello(){
        return "helloworld";
    }


}
