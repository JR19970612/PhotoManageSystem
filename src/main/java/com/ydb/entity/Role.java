package com.ydb.entity;

import java.util.List;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.entity
 * @date:2018/12/24
 */
public class Role {
    private Integer roleId;
    private String roleName;
    private List<ResourecesType> resourecesTypes;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<ResourecesType> getResourecesTypes() {
        return resourecesTypes;
    }

    public void setResourecesTypes(List<ResourecesType> resourecesTypes) {
        this.resourecesTypes = resourecesTypes;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", resourecesTypes=" + resourecesTypes +
                '}';
    }
}
