package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;

import java.io.Serializable;

/**
 * @program: com.ydb.entity
 * @description: Person
 * @author: Jun
 * @create: 2018-12-11 15:45
 **/
public class Person implements Serializable {
    @JsonView(SuccessView.class)
    private Integer personId;

    @JsonView(SuccessView.class)
    private String personName;

    @JsonView(SuccessView.class)
    private String personPassword;

    @JsonView(SuccessView.class)
    private String openId;

    @JsonView(SuccessView.class)
    private String personAvatarUrl;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword == null ? null : personPassword.trim();
    }

    public String getPersonAvatarUrl() {
        return personAvatarUrl;
    }

    public void setPersonAvatarUrl(String personAvatarUrl) {
        this.personAvatarUrl = personAvatarUrl == null ? null : personAvatarUrl.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", personPassword='" + personPassword + '\'' +
                ", openId='" + openId + '\'' +
                ", personAvatarUrl='" + personAvatarUrl + '\'' +
                '}';
    }
}