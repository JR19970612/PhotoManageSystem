package com.ydb.aspect;

import com.ydb.entity.Comment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.aspect
 * @date:2018/12/17
 */
public abstract class AbstractQueryCacheApsect<T> {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;

    //数据添加和更新的缓存切面
    private Integer updateCache(ProceedingJoinPoint point) {
        T data = (T) point.getArgs()[0];
        Integer result = 0;
        try {
            result = (Integer) point.proceed();
            if (result != 0) {
                updateCache(data);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    public abstract void updateCache(T t);

    //数据添加和更新的缓存切面
    private Integer deleteCache(ProceedingJoinPoint point) {
        Integer id = (Integer) point.getArgs()[0];
        Integer result = 0;
        try {
            result = (Integer) point.proceed();
            if (result != 0) {
                deleteCache(id);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    public abstract void deleteCache(Integer id);

    //在查询数据库之前先会查询缓存是否存在该数据
    public List<T> queryCacheBeforeDao(ProceedingJoinPoint point) {
        Integer photoId = (Integer) point.getArgs()[0];
    }
}
