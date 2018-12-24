package com.ydb.listener;

import com.ydb.aspect.CommentCacheAspect;
import com.ydb.aspect.PersonCacheAspect;
import com.ydb.aspect.PhotoCacheAspect;
import com.ydb.dao.ICommentDao;
import com.ydb.dao.IPersonDao;
import com.ydb.dao.IPhotoDao;
import com.ydb.entity.Comment;
import com.ydb.entity.Person;
import com.ydb.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: 应用启动后装载数据到缓存中，并到凌晨12在同步数据
 * @date:2018/12/22
 */

public class LoadingDataToRedis implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    IPhotoDao photoDao;
    @Autowired
    ICommentDao commentDao;
    @Autowired
    IPersonDao personDao;
    @Autowired
    PhotoCacheAspect photoCacheAspect;
    @Autowired
    CommentCacheAspect commentCacheAspect;
    @Autowired
    PersonCacheAspect personCacheAspect;

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("我的父容器为：" + contextRefreshedEvent.getApplicationContext().getParent());
        System.out.println("初始化时我被调用了。");
        List<Photo> photos = photoDao.selectAllPhoto().getResult();
        for (Photo photo : photos) {
            photoCacheAspect.update(photo);
            List<Comment> comments = commentDao.selectCommentByPhotoId(photo.getPhotoId());
            for (Comment comment : comments) {
                commentCacheAspect.update(comment);
            }
        }
        List<Person> peoples = personDao.queryPersons();
        for (Person people : peoples) {
            personCacheAspect.update(people);
        }
    }
}

