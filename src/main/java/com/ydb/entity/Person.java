package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;

/**
 * @program: com.ydb.entity
 * @description: Person
 * @author: Jun
 * @create: 2018-12-11 15:45
 **/
public class Person {
    @JsonView(ResultBean.SuccessView.class)
    private Integer personId;

    @JsonView(ResultBean.SuccessView.class)
    private String personName;

    @JsonView(ResultBean.SuccessView.class)
    private String personPassword;

    @JsonView(ResultBean.SuccessView.class)
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

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", personPassword='" + personPassword + '\'' +
                ", personAvatarUrl='" + personAvatarUrl + '\'' +
                '}';
    }
}