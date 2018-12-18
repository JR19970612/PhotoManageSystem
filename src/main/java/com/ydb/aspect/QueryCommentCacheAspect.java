package com.ydb.aspect;

import com.alibaba.fastjson.JSON;
import com.ydb.dao.IPersonDao;
import com.ydb.entity.Comment;
import com.ydb.entity.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;


/**
 * @author: create by JR
 * @version: v1.0
 * @description: Comment评论缓存切面
 * @date:2018/12/16
 */
public class QueryCommentCacheAspect extends AbstractQueryCacheApsect<Comment> {
    private String namespace = "comment:photoId:%d";//缓存命名空间

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;
    @Autowired
    IPersonDao personDao;

    //数据添加和更新的缓存切面
    @Override
    public void update(Comment comment) {
        Map<String, String> commentMap = new HashMap<>();
        commentMap.put("CommentId", String.valueOf(comment.getCommentId()));
        commentMap.put("PhotoId", String.valueOf(comment.getPhotoId()));
        commentMap.put("CommentContent", comment.getCommentContent());
        commentMap.put("CommentTime", comment.getCommentTime().toString());
        commentMap.put("PersonId", String.valueOf(comment.getPerson().getPersonId()));
        String commentJson = JSON.toJSONString(commentMap);
        hashOperations.put(String.format(namespace, comment.getPhotoId()), String.valueOf(comment.getCommentId()), commentJson);
    }

    //数据删除的缓存切面
    @Override
    public void delete(Comment comment) {
        hashOperations.delete(String.format(namespace, comment.getPhotoId()), String.valueOf(comment.getCommentId()));
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public List<Comment> queryCacheBeforeSelectDao(ProceedingJoinPoint point) throws Throwable {
        Integer photoId = (Integer) point.getArgs()[0];
        List<Comment> comments = new ArrayList<>();
        Set keys = redisTemplate.keys(String.format(namespace, photoId));
        if (keys != null & !keys.isEmpty()) {//若Redis缓存存在数据则直接读取返回
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                LinkedHashMap entriesMap = (LinkedHashMap) hashOperations.entries(key);
                for (Object objectKey : entriesMap.keySet()) {
                    String commentString = (String) entriesMap.get(objectKey);
                    Map<String, String> commentMap = (Map<String, String>) JSON.parse(commentString);
                    Comment comment = new Comment();
                    initObject(comment, commentMap);
                    Person person = personDao.queryPerson(Integer.valueOf(commentMap.get("PersonId")));
                    comment.setPerson(person);
                    comments.add(comment);
                }
            }
        } else {//不存在则去查询数据库中的数据
            comments = (List<Comment>) point.proceed();
        }
        return comments;
    }


}
