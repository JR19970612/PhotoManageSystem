package com.ydb.aspect;

import com.ydb.entity.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: person缓存切面
 * @date:2018/12/17
 */
public class PersonCacheAspect extends AbstractCacheApsect<Person> {
    private String namespace = "person:personId:%s:personName:%s";//缓存命名空间

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;


    @Override
    public void update(Person person) {
        if (person != null && person.getPersonId() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId(), person.getPersonName()), "PersonId", String.valueOf(person.getPersonId()));
        }
        if (person != null && person.getPersonName() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId(), person.getPersonName()), "PersonName", person.getPersonName());
        }
        if (person != null && person.getPersonPassword() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId(), person.getPersonName()), "PersonPassword", person.getPersonPassword());
        }
        if (person != null && person.getOpenId() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId(), person.getPersonName()), "OpenId", String.valueOf(person.getOpenId()));
        }
        if (person != null && person.getPersonAvatarUrl() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId(), person.getPersonName()), "PersonAvatarUrl", person.getPersonAvatarUrl());
        }
    }

    @Override
    public void delete(Person person) {
        redisTemplate.delete(String.format(namespace, person.getPersonId(), "*"));
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public Person queryCacheByArgsBeforeSelectDao(ProceedingJoinPoint point) throws Throwable {
        String args = (String) point.getArgs()[0];
        Set keys;
        if (args.startsWith("CACHE_TAG")) {
            keys = redisTemplate.keys(String.format(namespace, args.replace("CACHE_TAG", ""), "*"));
        } else {
            keys = redisTemplate.keys(String.format(namespace, "*", args));
        }
        Person person = new Person();
        if (keys != null && !keys.isEmpty()) {//若Redis缓存存在数据则直接读取返回
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Map personMap = hashOperations.entries(key);
                initObject(person, personMap);
            }
        } else {//不存在则去查询数据库中的数据
            if (args.startsWith("CACHE_TAG")) {
                person = (Person) point.proceed(new Object[]{args.replace("CACHE_TAG", "")});
            } else {
                person = (Person) point.proceed();
            }
        }
        return person;
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public List<Person> queryAllCacheBeforeSelectDao(ProceedingJoinPoint point) throws Throwable {
        List<Person> persons = new ArrayList<>();
        Set keys = redisTemplate.keys(String.format(namespace, "*", "*"));
        if (keys != null && !keys.isEmpty()) {//若Redis缓存存在数据则直接读取返回
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Person person = new Person();
                Map personMap = hashOperations.entries(key);
                initObject(person, personMap);
                persons.add(person);
            }
        } else {//不存在则去查询数据库中的数据
            persons = (List<Person>) point.proceed();
        }
        return persons;
    }
}
