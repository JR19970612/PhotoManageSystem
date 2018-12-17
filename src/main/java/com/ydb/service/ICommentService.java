package com.ydb.service;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Comment;

public interface ICommentService {
    ResultBean addComment(Comment comment);

    ResultBean dropComment(Comment comment);
}
