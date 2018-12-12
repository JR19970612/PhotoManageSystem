package com.ydb.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;

/**
 * @program: com.ydb.bean
 * @description: Admin
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
public class Admin {
    @JsonView(ResultBean.SuccessView.class)
    private Integer adminId;

    @JsonView(ResultBean.SuccessView.class)
    private String adminName;

    @JsonView(ResultBean.SuccessView.class)
    private String adminPassword;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }
}