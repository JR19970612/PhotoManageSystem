package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;

import java.util.Date;
import java.util.List;


public class Album {
    @JsonView(SuccessView.class)
    private Integer album_id;
    @JsonView(SuccessView.class)
    private String album_name;
    @JsonView(SuccessView.class)
    private String album_desc;
    @JsonView(SuccessView.class)
    private Date album_createtime;
    @JsonView(SuccessView.class)
    private List<Photo> photos;

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_desc() {
        return album_desc;
    }

    public void setAlbum_desc(String album_desc) {
        this.album_desc = album_desc;
    }

    public Date getAlbum_createtime() {
        return album_createtime;
    }

    public void setAlbum_createtime(Date album_createtime) {
        this.album_createtime = album_createtime;
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
                "album_id=" + album_id +
                ", album_name='" + album_name + '\'' +
                ", album_desc='" + album_desc + '\'' +
                ", album_createtime=" + album_createtime +
                ", photos=" + photos +
                '}';
    }
}
