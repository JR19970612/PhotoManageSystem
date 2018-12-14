package com.ydb.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ydb.JsonView.SuccessView;
import com.ydb.bean.ResultBean;
import com.ydb.entity.Person;
import com.ydb.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private IPersonService PersonService;

    @PostMapping("/person")
    @JsonView(SuccessView.class)
    public ResultBean<Person> insertPerson(Person Person) {
        return PersonService.insertPerson(Person);
    }


    @DeleteMapping("/person")
    @JsonView(SuccessView.class)
    public ResultBean<Person> deletePerson(Integer personId) {
        return PersonService.deletePerson(personId);
    }

    @PutMapping("/person")
    @JsonView(SuccessView.class)
    public ResultBean<Person> updatePerson(Person person) {
        return PersonService.updatePerson(person);
    }

    @GetMapping("/person")
    @JsonView(SuccessView.class)
    public ResultBean<List<Person>> queryPersons() {
        return PersonService.queryPersons();
    }

    @GetMapping("/person/{personId}")
    @JsonView(SuccessView.class)
    public ResultBean<Person> queryPerson(@PathVariable Integer personId) {
        return PersonService.queryPerson(personId);
    }

}
