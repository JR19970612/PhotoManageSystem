package com.ydb.service;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Admin;

public interface IAdminService {
    ResultBean<Admin> addAdmin(Admin admin);
}
