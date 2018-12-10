package com.ydb.dao;

import com.ydb.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPhotoDao {
    void insertPhoto(Photo photo) ;

    Integer deletePhoto(Integer id);

    Integer updatePhoto(Photo photo);

    Photo selectPhotoByName(String name);

    Photo selectPhotoById(Integer id);
}
