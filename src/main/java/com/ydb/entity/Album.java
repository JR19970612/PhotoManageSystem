package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;
import java.sql.Timestamp;



public class Album {
    @JsonView(ResultBean.SuccessView.class)
    private Integer album_id;
    @JsonView(ResultBean.SuccessView.class)
    private String album_name;
    @JsonView(ResultBean.SuccessView.class)
    private String album_desc;
    @JsonView(ResultBean.SuccessView.class)
    private Timestamp album_createtime;


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

    public Timestamp getAlbum_createtime() {
        return album_createtime;
    }

    public void setAlbum_createtime(Timestamp album_createtime) {
        this.album_createtime = album_createtime;
    }


}
