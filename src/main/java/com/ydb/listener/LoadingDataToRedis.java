package com.ydb.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 应用启动时装载数据到缓存中
 * @date:2018/12/22
 */
@Component
public class LoadingDataToRedis implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //TODO 应用启动时装载数据到缓存中
    }
}
