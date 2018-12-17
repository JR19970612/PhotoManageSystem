package com.ydb.aspect;

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
 * @description: person缓存切面
 * @date:2018/12/17
 */
public class QueryPersonCacheAspect {
    private String namespace = "person:personId:%s";//缓存命名空间

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    HashOperations hashOperations;

    public Integer updateCache(ProceedingJoinPoint point) {
        Person person = (Person) point.getArgs()[0];
        Integer reuslt = 0;
        try {
            reuslt = (Integer) point.proceed();
            if (reuslt != 0) {
                Map<String, Object> personMap = new HashMap<>();
                personMap.put("PersonId", person.getPersonId());
                personMap.put("PersonName", person.getPersonName());
                personMap.put("PersonPassword", person.getPersonPassword());
                personMap.put("PersonAvatarUrl", person.getPersonAvatarUrl());
                hashOperations.putAll(String.format(namespace, person.getPersonId()), personMap);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return reuslt;
    }

    public Integer deleteCache(ProceedingJoinPoint point) {
        Person person = (Person) point.getArgs()[0];
        Integer reuslt = 0;
        try {
            reuslt = (Integer) point.proceed();
            if (reuslt != 0) {
                redisTemplate.delete(String.format(namespace, person.getPersonId()));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return reuslt;
    }

    //在查询数据库之前先会查询缓存是否存在该数据
    public Person queryCacheBeforeSelectDao(ProceedingJoinPoint point) {
        Integer personId = (Integer) point.getArgs()[0];
        Person person = null;
        try {
            Set keys = redisTemplate.keys(String.format(namespace, personId));
            if (keys != null && !keys.isEmpty()) {
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    Map personMap = hashOperations.entries(key);
                    person = initPerson(personMap);
                }
            } else {
                person = (Person) point.proceed();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return person;
    }

    public Person initPerson(Map<String, String> entries) {
        Person person = new Person();
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            try {
                Method[] methods = person.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equals("set" + entry.getKey())) {
                        Class<?> param = method.getParameterTypes()[0];
                        switch (param.getSimpleName()) {
                            case "String": {
                                method.invoke(person, entry.getValue());
                                break;
                            }
                            case "Date": {
                                method.invoke(person, new Date(entry.getValue()));
                                break;
                            }
                            case "Integer": {
                                method.invoke(person, Integer.valueOf(entry.getValue()));
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
        return person;
    }
}
