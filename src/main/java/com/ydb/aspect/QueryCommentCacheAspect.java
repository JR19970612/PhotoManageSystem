package com.ydb.aspect;

import com.alibaba.fastjson.JSON;
import com.ydb.dao.IPersonDao;
import com.ydb.entity.Comment;
import com.ydb.entity.Person;
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
 * @description: Comment评论缓存切面
 * @date:2018/12/16
 */
public class QueryCommentCacheAspect extends AbstractQueryCacheApsect<Comment>{
    private String namespace = "comment:photoId:%s";//缓存命名空间

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;
    @Autowired
    IPersonDao personDao;

    //数据添加和更新的缓存切面
    public Integer updateCache(ProceedingJoinPoint point) {
        Comment comment = (Comment) point.getArgs()[0];
        Integer result = 0;
        try {
            result = (Integer) point.proceed();
            if (result > 0) {
                Map commentMap = new HashMap<>();
                commentMap.put("CommentId", comment.getCommentId());
                commentMap.put("PhotoId", comment.getPhotoId());
                commentMap.put("CommentContent", comment.getCommentContent());
                commentMap.put("CommentTime", comment.getCommentTime());
                commentMap.put("PersonId", comment.getPerson().getPersonId());
                String commentJson = JSON.toJSONString(commentMap);
                hashOperations.put(String.format(namespace, comment.getPhotoId()), String.valueOf(comment.getCommentId()), commentJson);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateCache(Comment comment) {

    }

    //数据删除的缓存切面
    public Integer deleteCache(ProceedingJoinPoint point) {
        Comment comment = (Comment) point.getArgs()[0];
        int result = 0;
        try {
            result = (int) point.proceed();
            if (result > 0) {
                hashOperations.delete(String.format(namespace, comment.getPhotoId()), comment.getCommentId());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteCache(Integer id) {

    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public List<Comment> queryCacheBeforeDao(ProceedingJoinPoint point) {
        Integer photoId = (Integer) point.getArgs()[0];
        List<Comment> comments = new ArrayList<>();
        try {
            Set keys = redisTemplate.keys(String.format(namespace, photoId));
            if (keys != null & !keys.isEmpty()) {
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Map commentMap = hashOperations.entries(key);
                    Comment comment = initComment(commentMap);
                    Person person = personDao.queryPerson((Integer) commentMap.get("PersonId"));
                    comment.setPerson(person);
                    comments.add(comment);
                }
            } else {
                comments = (List<Comment>) point.proceed();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return comments;
    }

    public Comment initComment(Map<String, String> entries) {
        Comment comment = new Comment();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            try {
                Method[] methods = comment.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equals("set" + entry.getKey())) {
                        Class<?> param = method.getParameterTypes()[0];
                        switch (param.getSimpleName()) {
                            case "String": {
                                method.invoke(comment, entry.getValue());
                                break;
                            }
                            case "Date": {
                                method.invoke(comment, new Date(entry.getValue()));
                                break;
                            }
                            case "Integer": {
                                method.invoke(comment, Integer.valueOf(entry.getValue()));
                                break;
                            }
                        }
                        break;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return comment;
    }
}
