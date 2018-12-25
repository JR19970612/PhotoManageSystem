package com.ydb.dao;

import com.ydb.entity.Person;
import com.ydb.entity.Role;

import java.util.List;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.dao
 * @date:2018/12/24
 */
public interface IRoleDao {
    List<Role> selectByPersonId(Person person);
}
