package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Album implements Serializable {
    @JsonView(SuccessView.class)
    private Integer albumId;
    @JsonView(SuccessView.class)
    private String albumName;
    @JsonView(SuccessView.class)
    private String albumDesc;
    @JsonView(SuccessView.class)
    private Date albumCreatetime;
    @JsonView(SuccessView.class)
    private List<Photo> photos;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public Date getAlbumCreatetime() {
        return albumCreatetime;
    }

    public void setAlbumCreatetime(Date albumCreatetime) {
        this.albumCreatetime = albumCreatetime;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", albumDesc='" + albumDesc + '\'' +
                ", albumCreatetime=" + albumCreatetime +
                ", photos=" + photos +
                '}';
    }
}
