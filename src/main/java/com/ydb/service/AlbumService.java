package com.ydb.service;

import com.ydb.dao.IAdminDao;
import com.ydb.dao.IAlbumDao;
import com.ydb.entity.Admin;
import com.ydb.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private IAlbumDao mapper;
    @Autowired
    public void setAlbumMapper(IAlbumDao mapper){

        this.mapper = mapper;
    }

    public void insertAlbum() {
        Album album=new Album();
        album.setAlbum_name("风景");
        album.setAlbum_desc("各大风景图");
        mapper.insertAlbum(album);


    }
}
