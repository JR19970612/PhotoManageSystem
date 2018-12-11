package com.ydb.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import java.sql.Timestamp;
import java.util.TimerTask;


public class Album {

    private Integer album_id;

    private String album_name;

    private String album_desc;

    private Timestamp album_createtime;

    private String albume_remark;

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

    public String getAlbume_remark() {
        return albume_remark;
    }

    public void setAlbume_remark(String albume_remark) {
        this.albume_remark = albume_remark;
    }
}
