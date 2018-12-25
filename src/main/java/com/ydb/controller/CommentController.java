package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Comment;
import com.ydb.service.ICommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "photoId", value = "评论图片ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "commentContent", value = "评论内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "person.personId", value = "评论用户ID", required = true, paramType = "query", dataType = "int"),
    }
    )
    @PostMapping(value = "/comment",params = {"photoId","commentContent","person.personId"})
    @JsonView(SuccessView.class)
    public ResultBean addComment(Comment comment) {
        return commentService.addComment(comment);
    }


    @ApiOperation(value = "删除评论", notes = "删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "photoId", value = "图片ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "commentId", value = "评论ID", required = true, paramType = "query", dataType = "int"),
    }
    )
    @DeleteMapping(value = "/comment",params = {"photoId","commentId"})
    @JsonView(SuccessView.class)
    public ResultBean dropComment(Comment comment) {
        return commentService.dropComment(comment);
    }
}
