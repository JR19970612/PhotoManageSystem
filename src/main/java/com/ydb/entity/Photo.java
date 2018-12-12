package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


public class Photo {
    @JsonView(ResultBean.SuccessView.class)
    private Integer photoId;

    @NotNull
    @JsonView(ResultBean.SuccessView.class)
    private String photoName;

    @NotNull
    @JsonView(ResultBean.SuccessView.class)
    private String photoDesc;

    @JsonView(ResultBean.SuccessView.class)
    private Date photoCreatetime;

    @NotNull
    @JsonView(ResultBean.SuccessView.class)
    private Integer albumId;

    @JsonView(ResultBean.SuccessView.class)
    private String photoOriginalUrl;

    @JsonView(ResultBean.SuccessView.class)
    private String photoThumUrl;

    @JsonView(ResultBean.SuccessView.class)
    private List<Comment> comments;


    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc;
    }

    public Date getPhotoCreatetime() {
        return photoCreatetime;
    }

    public void setPhotoCreatetime(Date photoCreatetime) {
        this.photoCreatetime = photoCreatetime;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getPhotoOriginalUrl() {
        return photoOriginalUrl;
    }

    public void setPhotoOriginalUrl(String photoOriginalUrl) {
        this.photoOriginalUrl = photoOriginalUrl;
    }

    public String getPhotoThumUrl() {
        return photoThumUrl;
    }

    public void setPhotoThumUrl(String photoThumUrl) {
        this.photoThumUrl = photoThumUrl;
    }

    public List<Comment> getComments() {

        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "photoId=" + photoId +
                ", photoName='" + photoName + '\'' +
                ", photoDesc='" + photoDesc + '\'' +
                ", photoCreatetime=" + photoCreatetime +
                ", albumId=" + albumId +
                ", photoOriginalUrl='" + photoOriginalUrl + '\'' +
                ", photoThumUrl='" + photoThumUrl + '\'' +
                ", comments=" + comments +
                '}';
    }
}
