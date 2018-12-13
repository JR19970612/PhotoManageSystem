package com.ydb.dao;

import com.ydb.entity.Album;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAlbumDao {
    int insertAlbum(Album album);

    int deleteAlbum(Integer album_id);

    int updateAlbum(Album album);

    Album selectAlbumById(Integer album_id);

    Album selectAlbumByName(String album_name);

    List<Album> selectAllAlbum();
}
