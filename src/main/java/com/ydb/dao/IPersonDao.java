package com.ydb.dao;


import com.ydb.entity.Person;

import java.util.List;

/**
 * @program: com.ydb.dao
 * @description: IPersonDao
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
//@Mapper
public interface IPersonDao {

    int insertPerson(Person person);

    List<Person> queryPersons();

    Person queryPersonByName(String personName);

    Person queryPersonById(String personId);

    int deletePerson(Person person);

    int updatePerson(Person person);

    //浏览器端登陆接口，该接口会查询用户的权限信息
    Person findPersonByUserNamePassword(String personName);

    //移动端登陆接口,该接口只查询简单用户信息
    Person findPersonByOpenId(String openId);

}