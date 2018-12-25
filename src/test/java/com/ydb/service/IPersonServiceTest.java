package com.ydb.service;

import com.ydb.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.service
 * @date:2018/12/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IPersonServiceTest {

    @Autowired
    IPersonService personService;

    @Test
    public void insertPerson() {
        Person person = new Person();
        person.setPersonName("SB");
        person.setPersonPassword("123456");
        person.setPersonAvatarUrl("http://localhost/image.url");
        personService.insertPerson(person);
    }
}