package com.ydb.dao;

import com.ydb.entity.ResourecesType;
import com.ydb.entity.ResourecesUrl;

import java.util.List;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.dao
 * @date:2018/12/24
 */
public interface IResourecesUrlDao {
    List<ResourecesUrl> selectByResourceTypeId(ResourecesType resourecesType);
}
