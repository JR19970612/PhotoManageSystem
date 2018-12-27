package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Person;
import com.ydb.service.IPersonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(
        origins = "*",
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = RequestMethod.POST,
        maxAge = 3600
)
@RestController
public class PersonController {

    @Autowired
    private IPersonService PersonService;

    //移动端用户注册接口
    @ApiOperation(value = "用户注册接口", notes = "注意！！！添加时需附带platform请求头，小程序值为iphone，浏览器为browser；如:platform=iphone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personName", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "openId", value = "openId", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "personAvatarUrl", value = "头像地址", required = false, paramType = "query", dataType = "String")
    }
    )
    @PostMapping(value = "/person", params = {"personName", "openId"}, headers = "platform=iphone")
    @JsonView({SuccessView.class})
    public ResultBean<Person> register(Person Person) {
        return PersonService.register(Person);
    }

    //后台管理员添加用户信息接口
    @ApiOperation(value = "用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personName", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "personPassword", value = "用户密码", required = true, paramType = "query", dataType = "String"),
    }
    )
    @PostMapping(value = "/person", params = {"personName", "personPassword"}, headers = "platform=browser")
    @JsonView({SuccessView.class})
    public ResultBean<Person> insert(Person Person, HttpServletResponse response) {
        return PersonService.insertPerson(Person);
    }


    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "用户ID", required = true, paramType = "path", dataType = "int"),
    }
    )
    @DeleteMapping(value = "/person/{personId}")
    @JsonView(SuccessView.class)
    public ResultBean<Person> deletePerson(@PathVariable Integer personId) {
        return PersonService.deletePerson(personId);
    }


    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "用户ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "personName", value = "用户名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "personPassword", value = "用户密码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "personAvatarUrl", value = "头像地址", required = false, paramType = "query", dataType = "String"),
    }
    )
    @PutMapping(value = "/person", params = {"personId"})
    @JsonView(SuccessView.class)
    public ModelAndView updatePerson(Person person) throws IOException {
        ResultBean<Person> resultBean = PersonService.updatePerson(person);
        ModelAndView modelAndView = new ModelAndView("redirectUserView", "status", resultBean.getStatus());
        return modelAndView;
    }

    @ApiOperation(value = "查询所有用户信息")
    @GetMapping("/persons")
    @JsonView(SuccessView.class)
    public ResultBean<Person> queryAllPersons() {
        return PersonService.queryPersons();
    }


    @ApiOperation(value = "根据用户名查询指定用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personName", value = "用户名", required = true, paramType = "query", dataType = "String"),
    }
    )
    @GetMapping(value = "/person", params = "personName")
    @JsonView(SuccessView.class)
    public ResultBean<Person> queryPersonByName(String personName) {
        return PersonService.queryPerson(personName);
    }


    @ApiOperation(value = "微信用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "微信openId", required = true, paramType = "query", dataType = "string"),
    }
    )
    @PostMapping(value = "/loginPerson")
    @JsonView(SuccessView.class)
    public ResultBean<Person> loginPerson(String openId) {
        return PersonService.loginPerson(openId);
    }
}
