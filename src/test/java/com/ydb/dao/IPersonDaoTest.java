package com.ydb.dao;

import com.ydb.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IPersonDaoTest {
    @Autowired
    IPersonDao personDao;
    @Autowired
    WebApplicationContext applicationContext;

    @Test
    public void insertPerson() {
        Person person = new Person();
        person.setPersonName("JR");
        person.setPersonPassword("123456");
        person.setPersonAvatarUrl("http://localhost/image.url");
        personDao.insertPerson(person);
//        char name = 'a' - 1;
//        for (int i = 0; i < 26; i++) {
//            name++;
//            Person person = new Person();
//            person.setPersonName(String.valueOf(name + "" + name + "" + name));
//            person.setPersonPassword("123456");
//            person.setPersonAvatarUrl("http://localhost/image.url");
//            personDao.insertPerson(person);
//        }
    }

    @Test
    public void deletePerson() {
        Person person = new Person();
        person.setPersonId(199);
        personDao.deletePerson(person);
    }

    @Test
    public void queryPerson() {
        System.out.println(personDao.queryPersonById("9"));
    }
}
