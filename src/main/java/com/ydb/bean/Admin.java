package com.ydb.bean;
/**
 * @program: com.ydb.bean
 * @description: Admin
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
public class Admin {
    private Integer adminId;

    private String adminName;

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