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


    @Override
    public ResultBean addComment(Comment comment) {
        comment.setCommentTime(new Date());
        ResultBean resultBean = new ResultBean();
        int code = commentDao.addComment(comment);
        initResultBean(code, resultBean);
        return resultBean;
    }

    @Override
    public ResultBean dropComment(Comment comment) {
        ResultBean resultBean = new ResultBean();
        int code = commentDao.deleteComment(comment);
        initResultBean(code, resultBean);
        return resultBean;
    }

    private void initResultBean(int code, ResultBean resultBean) {
        if (code > 0) {
            //插入数据成功
            resultBean.setStatus(ResultBean.SUCCSSED_CODE);
            resultBean.setMsg("操作成功");
        } else {
            resultBean.setStatus(ResultBean.FAILURE_CODE);
            resultBean.setMsg("操作失败");
        }
    }
}
