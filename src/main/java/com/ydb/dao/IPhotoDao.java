package com.ydb.dao;

import com.ydb.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPhotoDao {
    Photo insertPhoto(Photo photo);

    Integer deletePhoto(Integer id);

    Photo updatePhoto(Photo photo);

    Photo selectPhotoByName(String name);

    Photo selectPhotoById(Integer id);
}
