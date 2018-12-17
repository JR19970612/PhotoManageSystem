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

    Integer insertPerson(Person person);

    List<Person> queryPersons();

    Person queryPerson(Integer personId);

    Integer deletePerson(Person person);

    Integer updatePerson(Person person);

}