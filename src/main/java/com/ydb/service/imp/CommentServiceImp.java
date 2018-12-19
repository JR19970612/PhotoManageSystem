package com.ydb.service.imp;

import com.ydb.bean.ResultBean;
import com.ydb.dao.ICommentDao;
import com.ydb.entity.Comment;
import com.ydb.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImp implements ICommentService {
    @Autowired
    ICommentDao commentDao;

    ResultBean resultBean;

    @Override
    public ResultBean addComment(Comment comment) {
        int status = 0;
        String msg;
        comment.setCommentTime(new Date());
        status=commentDao.addComment(comment);
        if (status > 0) {
            msg = "添加成功";
            status = 0;
        } else {
            msg = "添加失败";
            status = -1;
        }
        //返回response
        resultBean = new ResultBean<>();
        resultBean.setStatus(status);
        resultBean.setMsg(msg);
        return resultBean;
    }

    @Override
    public ResultBean dropComment(Comment comment) {
        int status = 0;
        String msg; resultBean = new ResultBean();
        status=commentDao.deleteComment(comment);
        if (status > 0) {
            msg = "添加成功";
            status = 0;
        } else {
            msg = "添加失败";
            status = -1;
        }
        //返回response
        resultBean = new ResultBean<>();
        resultBean.setStatus(status);
        resultBean.setMsg(msg);
        return resultBean;
    }
}
