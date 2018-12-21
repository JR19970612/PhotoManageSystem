package com.ydb.service;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Person;

import java.util.List;

public interface IPersonService {
    //添加个人信息
    ResultBean<Person> insertPerson(Person person);

    //查询所有个人信息
    ResultBean<Person> queryPersons();

    //查询指定Id的个人信息
    ResultBean<Person> queryPerson(Integer personId);

    //删除指定Id的个人信息
    ResultBean<Person> deletePerson(Integer personId);

    //更新个人信息
    ResultBean<Person> updatePerson(Person person);

    ResultBean<Person> loginPerson(Person person);
}
