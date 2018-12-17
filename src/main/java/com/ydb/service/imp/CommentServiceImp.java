package com.ydb.service.imp;

import com.ydb.bean.ResultBean;
import com.ydb.dao.ICommentDao;
import com.ydb.entity.Comment;
import com.ydb.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements ICommentService {
    @Autowired
    ICommentDao commentDao;

    ResultBean resultBean;

    @Override
    public ResultBean addComment(Comment comment) {
        resultBean = new ResultBean();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("添加成功");
        commentDao.addComment(comment);
        return resultBean;
    }

    @Override
    public ResultBean dropComment(Comment comment) {
        resultBean = new ResultBean();
        resultBean.setStatus(ResultBean.SUCCSSED_CODE);
        resultBean.setMsg("删除成功");
        commentDao.deleteComment(comment);
        return resultBean;
    }
}
