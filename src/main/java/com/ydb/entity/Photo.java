package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.PhotoView;
import com.ydb.JsonView.SuccessView;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


public class Photo {
    @JsonView({PhotoView.QueryRoughly.class, SuccessView.class})
    private Integer photoId;

    @NotNull
    @JsonView({PhotoView.QueryRoughly.class,SuccessView.class})
    private String photoName;

    @NotNull
    @JsonView({PhotoView.QueryRoughly.class,SuccessView.class})
    private String photoDesc;

    @JsonView({PhotoView.QueryRoughly.class,SuccessView.class})
    private Date photoCreateTime;

    @NotNull
    @JsonView({PhotoView.QueryRoughly.class,SuccessView.class})
    private Integer albumId;

    @JsonView({PhotoView.QueryRoughly.class,SuccessView.class})
    private String photoOriginalUrl;

    @JsonView({PhotoView.QueryRoughly.class,SuccessView.class})
    private String photoThumUrl;

    @JsonView({PhotoView.QueryDetail.class})
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

    public Date getPhotoCreateTime() {
        return photoCreateTime;
    }

    public void setPhotoCreateTime(Date photoCreateTime) {
        this.photoCreateTime = photoCreateTime;
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
                ", photoCreateTime=" + photoCreateTime +
                ", albumId=" + albumId +
                ", photoOriginalUrl='" + photoOriginalUrl + '\'' +
                ", photoThumUrl='" + photoThumUrl + '\'' +
                ", comments=" + comments +
                '}';
    }
}
