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
    private String namespace = "person:personId:%s";//缓存命名空间

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;


    @Override
    public void update(Person person) {
        if (person != null && person.getPersonId() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId()), "PersonId", String.valueOf(person.getPersonId()));
        }
        if (person != null && person.getPersonName() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId()), "PersonName", person.getPersonName());
        }
        if (person != null && person.getPersonPassword() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId()), "PersonPassword", person.getPersonPassword());
        }
        if (person != null && person.getPersonAvatarUrl() != null) {
            hashOperations.put(String.format(namespace, person.getPersonId()), "PersonAvatarUrl", person.getPersonAvatarUrl());
        }
    }

    @Override
    public void delete(Person person) {
        redisTemplate.delete(String.format(namespace, person.getPersonId()));
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public Person queryCacheByIdBeforeSelectDao(ProceedingJoinPoint point) throws Throwable {
        Integer personId = (Integer) point.getArgs()[0];
        Person person = new Person();
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
        return person;
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public List<Person> queryAllCacheBeforeSelectDao(ProceedingJoinPoint point) throws Throwable {
        List<Person> persons = new ArrayList<>();
        Set keys = redisTemplate.keys(String.format(namespace, "*"));
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
