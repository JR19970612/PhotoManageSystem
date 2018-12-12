package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;

import java.util.Date;

public class Comment {
    @JsonView(SuccessView.class)
    private Integer commentId;
    @JsonView(SuccessView.class)
    private Integer photoId;
    @JsonView(SuccessView.class)
    private Date commentTime;
    @JsonView(SuccessView.class)
    private String commentContent;
    @JsonView(SuccessView.class)
    private Person person;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }


    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", photoId=" + photoId +
                ", commentTime=" + commentTime +
                ", commentContent='" + commentContent + '\'' +
                ", person=" + person +
                '}';
    }
}
