package com.ydb.aspect;

import com.ydb.entity.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: person缓存切面
 * @date:2018/12/17
 */
public class QueryPersonCacheAspect extends AbstractQueryCacheApsect<Person> {
    private String namespace = "person:personId:%d";//缓存命名空间

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;


    @Override
    public void update(Person person) {
        Map<String, String> personMap = new HashMap<>();
        personMap.put("PersonId", String.valueOf(person.getPersonId()));
        personMap.put("PersonName", person.getPersonName());
        personMap.put("PersonPassword", person.getPersonPassword());
        personMap.put("PersonAvatarUrl", person.getPersonAvatarUrl());
        hashOperations.putAll(String.format(namespace, person.getPersonId()), personMap);
    }

    @Override
    public void delete(Person person) {
        redisTemplate.delete(String.format(namespace, person.getPersonId()));
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public Person queryCacheBeforeSelectDao(ProceedingJoinPoint point) {
        Integer personId = (Integer) point.getArgs()[0];
        Person person = new Person();
        try {
            Set keys = redisTemplate.keys(String.format(namespace, personId));
            if (keys != null && !keys.isEmpty()) {//若Redis缓存存在数据则直接读取返回
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Map personMap = hashOperations.entries(key);
                    initObject(person, personMap);
                }
            } else {//不存在则去查询数据库中的数据
                person = (Person) point.proceed();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return person;
    }
}
