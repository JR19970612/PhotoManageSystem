package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Comment;
import com.ydb.service.ICommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    ICommentService commentService;

    @ApiOperation(value = "添加评论", notes = "添加评论")
    @PostMapping("/comment")
    @JsonView(SuccessView.class)
    public ResultBean addComment(Comment comment) {
        return commentService.addComment(comment);
    }


    @ApiOperation(value = "删除评论", notes = "删除评论")
    @DeleteMapping("/comment")
    @JsonView(SuccessView.class)
    public ResultBean dropComment(Comment comment) {
        return commentService.dropComment(comment);
    }
}
