package com.ydb.aspect;

import com.ydb.entity.Photo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: Photo信息缓存
 * @date:2018/12/16
 */

public class QueryPhotoCacheAspect {

    private String namespace = "photo:photoId-%s~photoName-%s";//缓存命名空间

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;

    //数据添加和更新的缓存切面
    public Integer updateCache(ProceedingJoinPoint point) {
        Photo photo = (Photo) point.getArgs()[0];
        Integer result = 0;//数据库操作受影响列
        try {
            result = (Integer) point.proceed(point.getArgs());
            if (result != 0) {//当数据真正对数据库产生影响时才进行缓存操作
                HashMap<String, String> photoMap = new HashMap<>();
                photoMap.put("PhotoId", String.valueOf(photo.getPhotoId()));
                photoMap.put("PhotoName", photo.getPhotoName());
                photoMap.put("AlbumId", String.valueOf(photo.getAlbumId()));
                photoMap.put("PhotoCreateTime", photo.getPhotoCreateTime().toString());
                photoMap.put("PhotoDesc", photo.getPhotoDesc());
                photoMap.put("PhotoOriginalUrl", photo.getPhotoOriginalUrl());
                photoMap.put("PhotoThumUrl", photo.getPhotoThumUrl());
                hashOperations.putAll(String.format(namespace, photo.getPhotoId(), photo.getPhotoName()), photoMap);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    //数据删除的缓存切面
    public Integer deleteCache(ProceedingJoinPoint point) {
        Integer photoId = (Integer) point.getArgs()[0];
        Integer result = 0;//数据库操作受影响列
        try {
            result = (Integer) point.proceed(point.getArgs());
            if (result != 0) {
                redisTemplate.delete(namespace + photoId);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public List<Photo> queryCacheBeforeSelectDao(ProceedingJoinPoint point) {
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
                String key = (String)keyIterator.next();
                Map entries = hashOperations.entries(key);
                Photo photo = initPhoto(entries);
                photos.add(photo);
            }
        } else {//不存在则去查询数据库中的数据
            try {
                photos = (List<Photo>) point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return photos;
    }

    //初始化Photo对象
    public Photo initPhoto(Map<String, String> entries) {
        Photo photo = new Photo();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            try {
                Method[] methods = photo.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equals("set" + entry.getKey())) {
                        Class<?> param = method.getParameterTypes()[0];
                        switch (param.getSimpleName()) {
                            case "String": {
                                method.invoke(photo, entry.getValue());
                                break;
                            }
                            case "Date": {
                                method.invoke(photo, new Date(entry.getValue()));
                                break;
                            }
                            case "Integer": {
                                method.invoke(photo, Integer.valueOf(entry.getValue()));
                                break;
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return photo;
    }
}