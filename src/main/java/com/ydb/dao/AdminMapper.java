package com.ydb.dao;

import com.ydb.bean.Admin;

/**
 * @program: com.ydb.dao
 * @description: AdminMapper
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}