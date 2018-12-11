package com.ydb.controller;

import com.ydb.dao.IAlbumDao;
import com.ydb.service.AdminService;
import com.ydb.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("/insertAlbum")
    @ResponseBody
    public String index(){
        albumService.insertAlbum();
        return "插入成功";
    }


}
