package com.ydb.dao;

import com.ydb.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.aspect
 * @date:2018/12/16
 */
@Mapper
public interface ICommentDao {
    int addComment(Comment comment);

    int deleteComment(Comment comment);

    List<Comment> selectCommentByPhotoId(Integer photoId);
}
