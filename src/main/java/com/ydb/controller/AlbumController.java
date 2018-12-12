package com.ydb.controller;

import com.ydb.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlbumController {
    @Autowired
    private IAlbumService albumService;
    @RequestMapping("/insertAlbum")
    @ResponseBody
    public String index(){
        albumService.insertAlbum();
        return "插入成功";
    }


}
