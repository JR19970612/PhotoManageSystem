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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
public class PersonController {

    @Autowired
    private IPersonService PersonService;


    @ApiOperation(value = "添加用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personName", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "personPassword", value = "用户密码", required = true, paramType = "query", dataType = "String"),
    }
    )
    @PostMapping(value = "/person", params = {"personName", "personPassword"})
    @JsonView({SuccessView.class})
    public ResultBean<Person> insertPerson(Person Person) {
        return PersonService.insertPerson(Person);
    }


    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "用户ID", required = true, paramType = "path", dataType = "int"),
    }
    )
    @DeleteMapping(value = "/person/{personId}", params = "personId")
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
    @PutMapping(value = "/person",params = {"personId"})
    @JsonView(SuccessView.class)
    public void updatePerson(HttpServletResponse response,Person person) throws IOException {
        ResultBean<Person> resultBean = PersonService.updatePerson(person);
        response.sendRedirect("/index.html?status="+resultBean.getStatus());
    }

    @ApiOperation(value = "查询所有用户信息")
    @GetMapping("/person")
    @JsonView(SuccessView.class)
    public ResultBean<Person> queryPersons() {
        return PersonService.queryPersons();
    }


    @ApiOperation(value = "根据用户ID查询指定用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "用户ID", required = true, paramType = "path", dataType = "int"),
    }
    )
    @GetMapping("/person/{personId}")
    @JsonView(SuccessView.class)
    public ResultBean<Person> queryPerson(@PathVariable Integer personId) {
        return PersonService.queryPerson(personId);
    }


    @ApiOperation(value = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personName", value = "用户名", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "personPassword", value = "用户密码", required = true, paramType = "query", dataType = "int"),
    }
    )
    @GetMapping(value = "/loginPerson",params = {"personName","personPassword"})
    @JsonView(SuccessView.class)
    public ResultBean<Person> loginPerson(Person person) {
        return PersonService.loginPerson(person);
    }

}
