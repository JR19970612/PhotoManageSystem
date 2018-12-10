package com.ydb.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class Photo {

    private Integer photoId;
    @NotNull
    private String photoName;
    @NotNull
    private String photoDesc;
    @NotNull
    @Past
    private Date photoCreatetime;
    @NotNull
    private Integer albumId;
    private String photoOriginalUrl;
    private String photoThumUrl;

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
                '}';
    }
}
