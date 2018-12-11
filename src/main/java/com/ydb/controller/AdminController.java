package com.ydb.controller;

import com.ydb.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xingbaichao on 15/12/10.
 */
@RestController
//@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @RequestMapping("/insertAdmin")
    @ResponseBody
    public String index(){
         adminService.insertAdmin();
          return "123";
    }

    @RequestMapping("/hello.do")
    public String doSome() {
        return "Hello world";
    }


}
