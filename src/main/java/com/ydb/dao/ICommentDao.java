package com.ydb.dao;

import com.ydb.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICommentDao {
    int addComment(Comment comment);

    int deleteComment(Comment comment);

    List<Comment> selectCommentByPhotoId(Integer photoId);

}
