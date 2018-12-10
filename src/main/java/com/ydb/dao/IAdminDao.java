package com.ydb.dao;

import com.ydb.entity.Admin;

/**
 * @program: com.ydb.dao
 * @description: IAdminDao
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
public interface IAdminDao {
    int deleteByPrimaryKey(Integer adminId);

    int insertAdmin(Admin admin);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}