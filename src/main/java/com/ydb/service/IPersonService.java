package com.ydb.service;

import com.ydb.bean.ResultBean;
import com.ydb.entity.Person;

public interface IPersonService {
    //微信注册突通用户信息
    ResultBean<Person> register(Person person);

    //超级管理员添加管理员信息
    ResultBean<Person> insertPerson(Person person);

    //查询所有个人信息
    ResultBean<Person> queryPersons();

    //查询指定Name的个人信息
    ResultBean<Person> queryPerson(String personName);

    //删除指定Id的个人信息
    ResultBean<Person> deletePerson(Integer personId);

    //更新个人信息
    ResultBean<Person> updatePerson(Person person);

    //微信端根据OpenId
    ResultBean<Person> loginPerson(String openId);

}
