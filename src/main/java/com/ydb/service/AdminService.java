package com.ydb.service;

import com.ydb.bean.Admin;
import com.ydb.dao.AdminMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: com.ydb.dao
 * @description: AdminService
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
@Service
public class AdminService {


    private AdminMapper mapper;


    @Autowired
    public void setUserMapper(AdminMapper mapper){
        this.mapper = mapper;
    }
    public void insert() {
       Admin admin = new Admin();
        admin.setAdminName("哈哈");
        admin.setAdminPassword("123456");
        mapper.insert(admin);


    }
}
