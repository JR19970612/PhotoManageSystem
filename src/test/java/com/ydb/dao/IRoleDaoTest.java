package com.ydb.dao;

import com.ydb.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: create by JR
 * @version: v1.0
 * @description: com.ydb.dao
 * @date:2018/12/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IRoleDaoTest {

    @Autowired
    IRoleDao roleDao;

    @Test
    public void selectRoleByPersonId() {
        Person person=new Person();
        person.setPersonId(10);
        System.out.println(roleDao.selectByPersonId(person));
    }
}