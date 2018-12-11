package com.ydb.dao;


import com.ydb.entity.Person;

import java.util.List;

/**
 * @program: com.ydb.dao
 * @description: IPersonDao
 * @author: Jun
 * @create: 2018-12-10 10:27
 **/
public interface IPersonDao {

    int insertPerson(Person person);

    List<Person> queryPersons();

    Person queryPerson(Integer personId);

    int deletePerson(Integer personId);

    int updatePerson(Person person);


//    int insertSelective(Person person);


//    int updateByPrimaryKeySelective(Person person);

}