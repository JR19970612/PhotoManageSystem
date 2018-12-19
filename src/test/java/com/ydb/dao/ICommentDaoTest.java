package com.ydb.dao;

import com.ydb.entity.Comment;
import com.ydb.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ICommentDaoTest {

    @Autowired
    ICommentDao iCommentDao;

    @Test
    public void addComment() {
        Person person = new Person();
        person.setPersonId(149);
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setPerson(person);
        comment.setCommentTime(new Date());
        comment.setPhotoId(51);
        comment.setCommentContent("Comment_Conent");
        iCommentDao.addComment(comment);
        System.out.println(comment);
    }

    @Test
    public void deleteComment() {
        Comment comment = new Comment();
        comment.setCommentId(19);
        comment.setPhotoId(51);
        iCommentDao.deleteComment(comment);
    }

    @Test
    public void selectCommentByPhotoId() {
        List<Comment> comment = iCommentDao.selectCommentByPhotoId(51);
        System.out.println(comment);
    }
}
