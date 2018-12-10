package com.ydb.controller;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Admin;
import com.ydb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xingbaichao on 15/12/10.
 */
@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/admin")
    public ResultBean<Admin> addAdmin(Admin admin) {
        return adminService.addAdmin(admin);
    }
}
