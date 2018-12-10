package com.ydb.controller;

import com.ydb.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xingbaichao on 15/12/10.
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @RequestMapping("/insert")
    @ResponseBody
    public String index(){

         adminService.insert();
 return "123";
    }

}
