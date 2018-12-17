package com.ydb.dao;

import com.ydb.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPhotoDao {
    void insertPhoto(Photo photo);

    Integer deletePhoto(Integer id);

    Integer updatePhoto(Photo photo);

    List<Photo> selectPhotoByName(String name);

    Photo selectPhotoById(Integer id);

    List<Photo> selectPhotoByAlbumId(Integer albumId);

    List<Photo> selectAllPhoto();
}
