package com.ydb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 缓存模板方法类
 * @date:2018/12/17
 */
public abstract class AbstractQueryCacheApsect<T> {

    //数据添加和更新的缓存切面
    public Integer updateCache(ProceedingJoinPoint point) throws Throwable {
        T data = (T) point.getArgs()[0];
        Integer result = 0;//数据库操作受影响列
        result = (Integer) point.proceed();
        if (result != 0) {//当数据真正对数据库产生影响时才进行缓存操作
            update(data);
        }
        return result;
    }

    public abstract void update(T t);

    //数据添加和更新的缓存切面
    public Integer deleteCache(ProceedingJoinPoint point) throws Throwable {
        T args = (T) point.getArgs()[0];
        Integer result = 0;//数据库操作受影响列
        result = (Integer) point.proceed();
        if (result != 0) {
            delete(args);
        }
        return result;
    }


    public abstract void delete(T args);

    //分组缓存数据到对象内
    public void initObject(T t, Map<String, String> entries) throws InvocationTargetException, IllegalAccessException {
        for (Map.Entry<String, String> entry : entries.entrySet()) {

            Method[] methods = t.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals("set" + entry.getKey())) {
                    Class<?> param = method.getParameterTypes()[0];
                    switch (param.getSimpleName()) {
                        case "String": {
                            method.invoke(t, entry.getValue());
                            break;
                        }
                        case "Date": {
                            method.invoke(t, new Date(entry.getValue()));
                            break;
                        }
                        case "Integer": {
                            method.invoke(t, Integer.valueOf(entry.getValue()));
                            break;
                        }
                    }
                }
            }
        }
    }
}
