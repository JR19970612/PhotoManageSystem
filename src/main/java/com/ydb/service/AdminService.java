package com.ydb.service;

import com.ydb.entity.Admin;
import com.ydb.dao.IAdminDao;

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


    private IAdminDao mapper;


    @Autowired
    public void setUserMapper(IAdminDao mapper){
        this.mapper = mapper;
    }
    public void insertAdmin() {
       Admin admin = new Admin();
//       admin.setAdminId(9);
        admin.setAdminName("哈哈11");
        admin.setAdminPassword("123456");
        mapper.insertAdmin(admin);


    }
}
