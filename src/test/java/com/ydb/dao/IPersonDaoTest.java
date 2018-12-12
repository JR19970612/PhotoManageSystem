package com.ydb.dao;

import com.ydb.entity.Person;
import javafx.scene.chart.Chart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IPersonDaoTest {
    @Autowired
    IPersonDao personDao;

    @Test
    public void insertPerson() {
        char name = 'a' - 1;
        for (int i = 0; i < 26; i++) {
            name++;
            Person person = new Person();
            person.setPersonName(String.valueOf(name + "" + name + "" + name));
            person.setPersonPassword("123456");
            person.setPersonAvatarUrl("http://localhost/image.url");
            personDao.insertPerson(person);
        }
    }

    @Test
    public void queryPerson() {
        System.out.println(personDao.queryPerson(109));
    }
}
