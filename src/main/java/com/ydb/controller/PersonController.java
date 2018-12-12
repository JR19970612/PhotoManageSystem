package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Person;
import com.ydb.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private IPersonService PersonService;

    @PostMapping("/insertPerson")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Person> insertPerson(Person Person) {
        return PersonService.insertPerson(Person);
    }

    @PostMapping("/queryPerson")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Person> queryPerson(Integer personId) {
        return PersonService.queryPerson(personId);
    }

    //    @DeleteMapping("/deletePerson")
    @PostMapping("/deletePerson")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Person> deletePerson(Integer personId) {
        return PersonService.deletePerson(personId);
    }

    @GetMapping("/queryPersons")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<List<Person>> queryPersons() {
        return PersonService.queryPersons();
    }

    //    @PutMapping("/updatePerson")
    @PostMapping("/updatePerson")
    @JsonView(ResultBean.SuccessView.class)
    public ResultBean<Person> updatePerson(Person person) {
        return PersonService.updatePerson(person);
    }
}
