package com.ydb.service.imp;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Admin;
import com.ydb.dao.IAdminDao;

import com.ydb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @program: com.ydb.dao
 * @description: AdminService
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
@Service
public class AdminServiceimp implements IAdminService {

    @Autowired
    private IAdminDao mapper;

    ResultBean<Admin> adminResultBean;


    public void insertAdmin() {

    }

    @Override
    public ResultBean<Admin> addAdmin(Admin admin) {
        adminResultBean = new ResultBean<>();
        adminResultBean.setStatus(ResultBean.SUCCSSED_CODE);
        adminResultBean.setMsg("添加成功");
        adminResultBean.setData(Arrays.asList(admin));
        mapper.insertAdmin(admin);
        return adminResultBean;
    }
}
