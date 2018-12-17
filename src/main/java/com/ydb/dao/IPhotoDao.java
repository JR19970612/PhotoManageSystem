package com.ydb.dao;

import com.github.pagehelper.Page;
import com.ydb.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.aspect
 * @date:2018/12/16
 */
@Mapper
public interface IPhotoDao {
    Integer insertPhoto(Photo photo);

    Integer deletePhoto(Integer id);

    Integer updatePhoto(Photo photo);

    List<Photo> selectPhotoByName(String name);

    List<Photo> selectPhotoById(Integer id);

    List<Photo> selectPhotoByAlbumId(Integer albumId);

    Page<Photo> selectAllPhoto();
}
