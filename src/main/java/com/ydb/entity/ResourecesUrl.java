package com.ydb.entity;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.entity
 * @date:2018/12/24
 */
public class ResourecesUrl {
    private Integer resourecesTypeId;
    private String resourecesUrl;
    private String resourecesUrlAction;
    private String resourecesUrlMethod;

    public Integer getResourecesTypeId() {
        return resourecesTypeId;
    }

    public void setResourecesTypeId(Integer resourecesTypeId) {
        this.resourecesTypeId = resourecesTypeId;
    }

    public String getResourecesUrl() {
        return resourecesUrl;
    }

    public void setResourecesUrl(String resourecesUrl) {
        this.resourecesUrl = resourecesUrl;
    }

    public String getResourecesUrlAction() {
        return resourecesUrlAction;
    }

    public void setResourecesUrlAction(String resourecesUrlAction) {
        this.resourecesUrlAction = resourecesUrlAction;
    }

    public String getResourecesUrlMethod() {
        return resourecesUrlMethod;
    }

    public void setResourecesUrlMethod(String resourecesUrlMethod) {
        this.resourecesUrlMethod = resourecesUrlMethod;
    }

    @Override
    public String toString() {
        return "ResourecesUrl{" +
                "resourecesTypeId=" + resourecesTypeId +
                ", resourecesUrl='" + resourecesUrl + '\'' +
                ", resourecesUrlAction='" + resourecesUrlAction + '\'' +
                ", resourecesUrlMethod='" + resourecesUrlMethod + '\'' +
                '}';
    }
}
