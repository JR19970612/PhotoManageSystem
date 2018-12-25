package com.ydb.entity;

import java.util.List;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.entity
 * @date:2018/12/24
 */
public class ResourecesType {
    private Integer resourecesTypeId;
    private String resoureceTypeName;
    private List<ResourecesUrl> resourecesUrls;

    public Integer getResourecesTypeId() {
        return resourecesTypeId;
    }

    public void setResourecesTypeId(Integer resourecesTypeId) {
        this.resourecesTypeId = resourecesTypeId;
    }

    public String getResoureceTypeName() {
        return resoureceTypeName;
    }

    public void setResoureceTypeName(String resoureceTypeName) {
        this.resoureceTypeName = resoureceTypeName;
    }

    public List<ResourecesUrl> getResourecesUrls() {
        return resourecesUrls;
    }

    public void setResourecesUrls(List<ResourecesUrl> resourecesUrls) {
        this.resourecesUrls = resourecesUrls;
    }

    @Override
    public String toString() {
        return "ResourecesType{" +
                "resourecesTypeId=" + resourecesTypeId +
                ", resoureceTypeName='" + resoureceTypeName + '\'' +
                ", resourecesUrls=" + resourecesUrls +
                '}';
    }
}
