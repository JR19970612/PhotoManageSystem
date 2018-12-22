package com.ydb.aspect;

import com.ydb.dao.ICommentDao;
import com.ydb.entity.Comment;
import com.ydb.entity.Photo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: Photo图片信息缓存
 * @date:2018/12/16
 */

public class PhotoCacheAspect extends AbstractCacheApsect<Photo> {

    private String namespace = "photo:photoId:%s:photoName:%s";//缓存命名空间

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;
    @Autowired
    ICommentDao commentDao;

    @Override
    public void update(Photo photo) {
        if (photo != null && photo.getPhotoId() != null) {
            hashOperations.put(String.format(namespace, photo.getPhotoId(),photo.getPhotoName()), "PhotoId", String.valueOf(photo.getPhotoId()));
        }
        if (photo != null && photo.getPhotoName() != null) {
            hashOperations.put(String.format(namespace, photo.getPhotoId(),photo.getPhotoName()), "PhotoName", photo.getPhotoName());
        }
        if (photo != null && photo.getAlbumId() != null) {
            hashOperations.put(String.format(namespace, photo.getPhotoId(),photo.getPhotoName()), "AlbumId", String.valueOf(photo.getAlbumId()));
        }
        if (photo != null && photo.getPhotoDesc() != null) {
            hashOperations.put(String.format(namespace, photo.getPhotoId(),photo.getPhotoName()), "PhotoDesc", photo.getPhotoDesc());
        }
        if (photo != null && photo.getPhotoCreateTime() != null) {
            hashOperations.put(String.format(namespace, photo.getPhotoId(),photo.getPhotoName()), "PhotoCreateTime", photo.getPhotoCreateTime().toString());
        }
        if (photo != null && photo.getPhotoOriginalUrl() != null) {
            hashOperations.put(String.format(namespace, photo.getPhotoId(),photo.getPhotoName()), "PhotoOriginalUrl", photo.getPhotoOriginalUrl());
        }
        if (photo != null && photo.getPhotoThumUrl() != null) {
            hashOperations.put(String.format(namespace, photo.getPhotoId(),photo.getPhotoName()), "PhotoThumUrl", photo.getPhotoThumUrl());
        }
    }


    @Override
    public void delete(Photo photo) {
        redisTemplate.delete(String.format(namespace, photo.getPhotoId(), photo.getPhotoName()));
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public List<Photo> queryCacheBeforeSelectDao(ProceedingJoinPoint point) throws Throwable {
        Object args = point.getArgs()[0];
        List<Photo> photos = new ArrayList<>();
        Set keys;
        if (args instanceof Integer) {//根据photoId查询
            keys = redisTemplate.keys(String.format(namespace, args, "*"));
        } else {//根据photoName查询
            keys = redisTemplate.keys(String.format(namespace, "*", args));
        }
        if (keys != null && !keys.isEmpty()) {//若Redis缓存存在数据则直接读取返回
            Iterator keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                String key = (String) keyIterator.next();
                Map photoMap = hashOperations.entries(key);
                Photo photo = new Photo();
                initObject(photo, photoMap);
                //查询Comment评论信息
                List<Comment> comments = commentDao.selectCommentByPhotoId(photo.getPhotoId());
                photo.setComments(comments);
                photos.add(photo);
            }
        } else {//不存在则去查询数据库中的数据
            photos = (List<Photo>) point.proceed();
        }
        return photos;
    }
}